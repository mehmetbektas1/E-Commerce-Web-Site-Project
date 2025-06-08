document.addEventListener("DOMContentLoaded", function() {
  const token = localStorage.getItem("access_token");
  if (!token) return;

  fetch("http://localhost:8080/rest/api/product/list", {
    method: "GET",
    headers: {
      "Authorization": "Bearer " + token,
      "Content-Type": "application/json"
    }
  })
  .then(res => {
    if (!res.ok) throw new Error("Ürün verisi alınamadı. Status: " + res.status);
    return res.json();
  })
  .then(products => {
    window.productList = products;
    localStorage.setItem("productList", JSON.stringify(products)); // <-- ürünleri kaydet

  })
  .catch(error => {
    console.error("Hata:", error);
  });
});

