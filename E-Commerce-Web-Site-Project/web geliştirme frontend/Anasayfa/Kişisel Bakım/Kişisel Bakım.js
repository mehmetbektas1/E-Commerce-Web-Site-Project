document.addEventListener("DOMContentLoaded", function () {
  const productListString = localStorage.getItem("productList");

  if (!productListString) {
    document.getElementById("kisisel-bakım-container").innerHTML =
      "<p>Ürünler henüz yüklenmedi. Lütfen önce ürünlerin yüklendiği sayfaya gidin.</p>";
    return;
  }

  let parsedData;
  try {
    parsedData = JSON.parse(productListString);
  } catch (e) {
    document.getElementById("kisisel-bakim-container").innerHTML =
      "<p>Geçersiz ürün verisi bulundu. JSON hatası.</p>";
    return;
  }

  const productList = parsedData.payload || [];

  const petProducts = productList.filter(
    (p) => p.productCategory === "Kişisel Bakım"
  );

  const container = document.getElementById("kisisel-bakim-container");

  if (!petProducts.length) {
    container.innerHTML =
      "<p>Kişisel Bakım kategorisinde ürün bulunamadı.</p>";
    return;
  }

const style = document.createElement("style");
style.innerHTML = `
  .product-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 20px;
    justify-items: center;
    margin-top: 20px;
  }

  .product-card {
    border: 1px solid #e0e0e0;
    border-radius: 12px;
    padding: 15px;
    width: 250px;
    background-color: #ffffff;
    transition: transform 0.3s, box-shadow 0.3s;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.08);
    position: relative;
  }

  .product-card:hover {
    transform: scale(1.02);
  }

  .product-image {
    width: 100%;
    height: 150px;
    background-color: #dcdcdc;
    border-radius: 8px;
    margin-bottom: 10px;
  }

  .product-name {
    font-weight: 700;
    font-size: 18px;
    margin-bottom: 5px;
    color: #222;
    text-align: center;
  }

  .product-price {
    color: #0d6efd;
    font-weight: bold;
    margin-bottom: 8px;
    text-align: center;
  }

  .product-description {
    font-size: 14px;
    color: #555;
    margin-bottom: 10px;
    text-align: center;
  }

  .product-details {
    font-size: 14px;
    color: #444;
    border-top: 1px solid #ddd;
    padding-top: 10px;
    margin-top: 10px;
  }

  .add-to-cart-btn {
    width: 100%;
    margin-top: 10px;
    padding: 10px;
    background-color: #ff6600;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 15px;
    transition: background-color 0.3s;
  }

  .add-to-cart-btn:hover {
    background-color: #e65c00;
  }

  @media (max-width: 1100px) {
    .product-grid {
      grid-template-columns: repeat(3, 1fr);
    }
  }

  @media (max-width: 800px) {
    .product-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }

  @media (max-width: 500px) {
    .product-grid {
      grid-template-columns: 1fr;
    }
  }
`;
document.head.appendChild(style);

  const productCards = petProducts.map((product) => `
    <div class="product-card" data-id="${product.id}">
      <div class="product-image"></div>
      <div class="product-name">${product.productName}</div>
      <div class="product-price">${product.price} ${product.currencyType}</div>
      <div class="product-description">${product.productDescription}</div>
      <div class="product-details">
        <p><strong>Üretim Yılı:</strong> ${product.productionYear}</p>
        <p><strong>Durum:</strong> ${product.productStatusType}</p>
        <button class="add-to-cart-btn">Sepete Ekle</button>
      </div>
    </div>
  `).join("");

  container.innerHTML = `<div class="product-grid">${productCards}</div>`;

  // Sepete ekleme işlemi
document.querySelectorAll(".add-to-cart-btn").forEach((btn) => {
  btn.addEventListener("click", async function (e) {
    e.stopPropagation();

    const productCard = this.closest(".product-card");
    const productId = productCard.getAttribute("data-id");
    const customerId = localStorage.getItem("user_id");
    const accessToken = localStorage.getItem("access_token");

    if (!customerId || !accessToken) {
      alert("Giriş yapmanız gerekiyor.");
      return;
    }

    const payload = {
      customerId: customerId,
      productId: productId
    };

    try {
      const response = await fetch("http://localhost:8080/rest/api/sepet/save", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${accessToken}`
        },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        this.outerHTML = `<div style="color: green; font-weight: bold; text-align: center;">✅ Sepete Eklendi</div>`;
      } else {
        const errorText = await response.text();
        alert("Ürün sepete eklenemedi. Sunucu hatası:\n" + errorText);
      }
    } catch (error) {
      console.error("Hata:", error);
      alert("Bir hata oluştu. Lütfen tekrar deneyin.");
    }
  });
});

});



