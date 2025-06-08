fetch('categorybar/categorybar.html')
  .then(res => res.text())
  .then(html => {
    document.getElementById('categorybar-container').innerHTML = html;

    // Ana kategori butonları için yönlendirme
    const categoryBtns = document.querySelectorAll('.category-btn');
    categoryBtns.forEach(btn => {
      const text = btn.textContent.trim().toLowerCase();
      if (text === "ev & yaşam") {
        btn.addEventListener('click', () => {
          window.location.href = 'Ev&Yaşam/Ev&Yaşam.html';
        });
      }
      if (text === "kozmetik") {
        btn.addEventListener('click', () => {
          window.location.href = 'Kozmetik/Kozmetik.html';
        });
      }
      if (text === "spor") {
        btn.addEventListener('click', () => {
          window.location.href = 'Spor/Spor.html';
        });
      }
      if (text === "moda") {
        btn.addEventListener('click', () => {
          window.location.href = 'Moda/Moda.html';
        });
      }
      if (text === "ayakkabı & çanta") {
        btn.addEventListener('click', () => {
          window.location.href = 'Ayakkabı/Ayakkabı&Çanta.html';
        });
      }
      if (text === "bahçe") {
        btn.addEventListener('click', () => {
          window.location.href = 'Bahçe/Bahçe.html';
        });
      }
      if (text === "kişisel bakım") {
        btn.addEventListener('click', () => {
          window.location.href = 'Kişisel Bakım/Kişisel Bakım.html';
        });
      }
      if (text === "pet shop") {
        btn.addEventListener('click', () => {
          window.location.href = 'Pet Shop/Pet Shop.html';
        });
      }
    });

    // Tüm Kategoriler butonuna tıklanınca modalı aç
    const allCategoriesBtn = document.getElementById('allCategoriesBtn');
    const categoriesModal = document.getElementById('categories-modal');
    const closeModalBtn = document.getElementById('closeCategoriesModal');

    if (allCategoriesBtn && categoriesModal) {
      allCategoriesBtn.addEventListener('click', () => {
        categoriesModal.style.display = 'block';
      });
    }
    if (closeModalBtn && categoriesModal) {
      closeModalBtn.addEventListener('click', () => {
        categoriesModal.style.display = 'none';
      });
    }
    // Modal dışında bir yere tıklanınca modalı kapat
    window.addEventListener('click', function(e) {
      if (categoriesModal && e.target === categoriesModal) {
        categoriesModal.style.display = 'none';
      }
    });

    // Modal içindeki kategori listesinden seçim yapılınca yönlendir
    const modalCategoryItems = document.querySelectorAll('.categories-list li[data-category]');
    modalCategoryItems.forEach(item => {
      item.addEventListener('click', function() {
        const page = item.getAttribute('data-category');
        if (page) {
          window.location.href = page;
        }
      });
    });
  });