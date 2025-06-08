document.addEventListener("DOMContentLoaded", () => {
  const cartItemsContainer = document.getElementById("cart-items");
  const totalPriceElement = document.getElementById("total-price");
  const checkoutBtn = document.getElementById("checkout-btn");

  const token = localStorage.getItem("access_token");
  const customerId = localStorage.getItem("user_id");

  async function fetchCart() {
    if (!token || !customerId) {
      cartItemsContainer.innerHTML = '<p class="empty">Giriş yapmalısınız.</p>';
      totalPriceElement.textContent = "0 TL";
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/rest/api/sepet/list/${customerId}`, {
        headers: {
          "Authorization": `Bearer ${token}`
        }
      });

      if (!response.ok) {
        throw new Error("Sepet verileri alınamadı.");
      }

      const result = await response.json();
      const cart = result.payload;
      renderCart(cart);
    } catch (error) {
      cartItemsContainer.innerHTML = `<p class="empty">Hata oluştu: ${error.message}</p>`;
      totalPriceElement.textContent = "0 TL";
    }
  }

  async function deleteCartItem(shopId) {
    try {
      const response = await fetch(`http://localhost:8080/rest/api/sepet/delete/${shopId}`, {
        method: "DELETE",
        headers: {
          "Authorization": `Bearer ${token}`
        }
      });

      if (response.ok) {
        fetchCart();
      } else {
        alert("Ürün silinemedi. Sunucu hatası.");
      }
    } catch (err) {
      alert("Ürün silinemedi: " + err.message);
    }
  }

  function renderCart(cart) {
    cartItemsContainer.innerHTML = "";
    let total = 0;

    if (!cart || cart.length === 0) {
      cartItemsContainer.innerHTML = '<p class="empty">Sepetinizde ürün bulunmamaktadır.</p>';
      totalPriceElement.textContent = "0 TL";
      return;
    }

    cart.forEach(item => {
      const itemDiv = document.createElement("div");
      itemDiv.classList.add("cart-item");

      itemDiv.innerHTML = `
        <img src="${item.product.image || 'placeholder.jpg'}" alt="${item.product.productName}" />
        <div class="cart-info">
          <h3>${item.product.productName}</h3>
          <p>${item.product.price} TL</p>
        </div>
        <button class="remove-btn" data-id="${item.id}" style="background:#e53935;color:#fff;border:none;padding:8px 14px;border-radius:5px;cursor:pointer;">Sil</button>
      `;

      total += parseFloat(item.product.price);
      cartItemsContainer.appendChild(itemDiv);
    });

    totalPriceElement.textContent = `${total.toFixed(2)} TL`;
  }

  cartItemsContainer.addEventListener("click", function (e) {
    if (e.target.classList.contains("remove-btn")) {
      const shopId = e.target.getAttribute("data-id");
      deleteCartItem(shopId);
    }
  });

  if (checkoutBtn) {
    checkoutBtn.addEventListener("click", async function () {
      try {
        const cartResponse = await fetch(`http://localhost:8080/rest/api/sepet/list/${customerId}`, {
          headers: {
            "Authorization": `Bearer ${token}`
          }
        });

        if (!cartResponse.ok) {
          throw new Error("Sepet bilgileri alınamadı.");
        }

        const cartResult = await cartResponse.json();
        const cart = cartResult.payload;

        if (!cart || cart.length === 0) {
          alert("Sepetiniz boş.");
          return;
        }

        for (const item of cart) {
          const productId = item.product.id;

          const sellerRes = await fetch(`http://localhost:8080/rest/api/seller-product/list/${productId}`, {
            headers: {
              "Authorization": `Bearer ${token}`
            }
          });

          if (!sellerRes.ok) {
            throw new Error(`Seller bilgisi alınamadı. Ürün ID: ${productId}`);
          }

          const sellerJson = await sellerRes.json();
          const sellerId = sellerJson.payload;

          if (!sellerId) {
            throw new Error(`Geçersiz sellerId. Ürün ID: ${productId}`);
          }

          const saleRes = await fetch("http://localhost:8080/rest/api/saled-product/save", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
              customerId: parseInt(customerId),
              sellerId: parseInt(sellerId)
            })
          });

          const saleJson = await saleRes.json();

          if (saleRes.ok && saleJson.status === 200) {
            console.log("Satış başarıyla kaydedildi:", saleJson.payload);
          } else {
            throw new Error("Satış kaydedilemedi: " + (saleJson.errorMessage || "Bilinmeyen hata"));
          }
        }

        alert("Ödeme işlemi başarıyla tamamlandı!");
        fetchCart(); // sepeti temizle/güncelle

      } catch (error) {
        console.error(error);
        alert("Ödeme sırasında hata oluştu: " + error.message);
      }
    });
  }

  fetchCart();
});
