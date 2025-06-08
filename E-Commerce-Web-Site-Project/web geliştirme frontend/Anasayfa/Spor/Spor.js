document.addEventListener("DOMContentLoaded", function() {
  fetch("../products/products.json")
    .then(res => res.json())
    .then(products => {
      const sporProducts = products.filter(p => p.category === "Spor");
      const container = document.getElementById("spor-products");

      if (sporProducts.length === 0) {
        container.innerHTML = "<p>Bu kategoride ürün yok.</p>";
        return;
      }

   // ...existing code...
container.innerHTML = sporProducts.map(product => `
  <div class="product-detail">
    <a href="../products/products.html?id=${product.id}">
      <img src="${product.image}" alt="${product.name}" />
    </a>
    <div class="product-info">
      <h2>${product.name}</h2>
      <p>${product.price} TL</p>
      <p>${product.description}</p>
      <button class="add-to-cart-btn" onclick="addToCart('${product.id}')">Sepete Ekle</button>
    </div>
  </div>
`).join("");
// ...existing code...
    });
});

function addToCart(productId) {
  fetch("../products/products.json")
    .then(res => res.json())
    .then(products => {
      const product = products.find(p => p.id === productId);
      if (!product) return;
      let cart = JSON.parse(localStorage.getItem("cart")) || [];
      cart.push(product);
      localStorage.setItem("cart", JSON.stringify(cart));
      alert("Ürün sepete eklendi!");
    });
}