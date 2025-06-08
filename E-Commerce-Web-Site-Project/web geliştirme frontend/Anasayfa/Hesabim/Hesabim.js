document.addEventListener("DOMContentLoaded", function() {
  // Kullanıcı bilgilerini localStorage'dan al
  const username = localStorage.getItem("username") || "";
  const email = localStorage.getItem("email") || "";
  const password = localStorage.getItem("password") || "";

  document.getElementById("username").value = username;
  document.getElementById("email").value = email;
  document.getElementById("password").value = password;

  // Bilgileri güncelle
  document.getElementById("account-form").addEventListener("submit", function(e) {
    e.preventDefault();
    localStorage.setItem("username", document.getElementById("username").value);
    localStorage.setItem("email", document.getElementById("email").value);
    localStorage.setItem("password", document.getElementById("password").value);
    alert("Bilgileriniz güncellendi!");
  });

  // Çıkış yap
  document.getElementById("logout-btn").addEventListener("click", function() {
    localStorage.removeItem("username");
    localStorage.removeItem("email");
    localStorage.removeItem("password");
    window.location.href = "../Anasayfa1.html";
  });
});