// filepath: c:\Users\aa\OneDrive\Masaüstü\web geliştirme frontend\Anasayfa\colorfulbar.js
fetch('colorfulbar/colorfulbar.html')
  .then(res => res.text())
  .then(html => document.getElementById('colorfulbar-container').innerHTML = html);