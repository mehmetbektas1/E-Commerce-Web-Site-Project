fetch('flashproducts/flashproducts.html')
  .then(res => res.text())
  .then(html => {
    document.getElementById('flashproducts-container').innerHTML = html;

    // HTML içerik DOM'a eklendikten sonra bu kodlar çalışmalı!
    const carousel = document.getElementById('carousel');
    const btnNext = document.getElementById('btnNext');
    const btnPrev = document.getElementById('btnPrev');
    const scrollAmount = 220;

    function scrollCarousel(direction) {
      const maxScrollLeft = carousel.scrollWidth - carousel.clientWidth;
      const newScrollLeft = carousel.scrollLeft + direction * scrollAmount;

      if (newScrollLeft > maxScrollLeft) {
        carousel.scrollTo({ left: 0, behavior: 'smooth' });
      } else if (newScrollLeft < 0) {
        carousel.scrollTo({ left: maxScrollLeft, behavior: 'smooth' });
      } else {
        carousel.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
      }
    }

    btnNext.addEventListener('click', () => scrollCarousel(1));
    btnPrev.addEventListener('click', () => scrollCarousel(-1));

    setInterval(() => {
      scrollCarousel(1);
    }, 4000);
  });
