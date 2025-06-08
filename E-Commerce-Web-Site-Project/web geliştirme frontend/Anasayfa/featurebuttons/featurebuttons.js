// filepath: c:\Users\aa\OneDrive\Masaüstü\web geliştirme frontend\Anasayfa\featurebuttons.js
fetch('featurebuttons/featurebuttons.html')
  .then(res => res.text())
  .then(html => document.getElementById('featurebuttons-container').innerHTML = html);