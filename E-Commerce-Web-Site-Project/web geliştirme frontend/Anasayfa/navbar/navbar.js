fetch('navbar/navbar.html')
  .then(res => res.text())
  .then(html => {
    document.getElementById('navbar-container').innerHTML = html;

    // Giriş butonu kontrolü
    const loginBtn = document.getElementById('login-btn');
    const username = localStorage.getItem('username');


    if (loginBtn) {
     if (username) {
      loginBtn.textContent = username;
      loginBtn.onclick = () => {
      window.location.href = 'hesabim/hesabim.html';
     };
    } 
    else {
    loginBtn.addEventListener('click', () => {
      window.location.href = '../Giriş/Giriş.html';
    });
  }
}

    // Sepetim butonuna tıklanınca yönlendir
    const cartBtn = document.getElementById('cart-btn');
    if (cartBtn) {
      cartBtn.addEventListener('click', () => {
        window.location.href = 'sepet/sepet.html';
      });
    }

    // ARAMA BUTONU EVENTİNİ BURADA EKLEYİN!
    const searchInput = document.getElementById("search-input");
    if (searchInput) {
      searchInput.addEventListener("keydown", function(e) {
        if (e.key === "Enter") {
          const query = searchInput.value.trim().toLowerCase();
          if (!query) return;

          fetch('products/products.json')
            .then(res => res.json())
            .then(products => {
              // Önce ürün adı ile tam eşleşme ara
              const foundProduct = products.find(p => p.name && p.name.toLowerCase().includes(query));
              if (foundProduct) {
                window.location.href = `products/products.html?id=${foundProduct.id}`;
                return;
              }
              // Sonra kategori ile eşleşme ara
              const foundCategory = products.find(p => p.category && p.category.toLowerCase().includes(query));
              if (foundCategory) {
                let page = "";
                switch (foundCategory.category.toLowerCase()) {
                  case "kozmetik": page = "Kozmetik/Kozmetik.html"; break;
                  case "spor": page = "Spor/Spor.html"; break;
                  case "moda": page = "Moda/Moda.html"; break;
                  case "ev & yaşam": page = "Ev&Yaşam/Ev&Yaşam.html"; break;
                  case "pet shop": page = "Pet Shop/Pet Shop.html"; break;
                  case "bahçe": page = "Bahçe/Bahçe.html"; break;
                  case "kişisel bakım": page = "Kişisel Bakım/Kişisel Bakım.html"; break;
                  case "ayakkabı & çanta": page = "Ayakkabı/Ayakkabı&Çanta.html"; break;
                  default: page = ""; break;
                }
                if (page) window.location.href = page;
                else alert("Kategoriye ait sayfa bulunamadı.");
                return;
              }
              alert("Aradığınız ürün veya kategori bulunamadı.");
            });
        }
      });
    }
  });
