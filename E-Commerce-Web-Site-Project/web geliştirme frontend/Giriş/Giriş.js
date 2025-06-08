 document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    try {
      const response = await fetch('http://localhost:8080/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      });

      const tokenDiv = document.getElementById('tokenOutput');
      const errorDiv = document.getElementById('errorMsg');

      if (response.ok) {
        const data = await response.json();

        const accessToken = data.payload?.accessToken;
        const refreshToken = data.payload?.refreshToken;
        const userId = data.payload?.user_id;

        if (!accessToken) {
          tokenDiv.textContent = "accessToken bulunamadı. Backend yanıtı hatalı.";
          tokenDiv.style.color = "red";
          return;
        }

        localStorage.setItem('access_token', accessToken);
        localStorage.setItem('refresh_token', refreshToken);
        localStorage.setItem('user_id', userId);


        tokenDiv.textContent = "Giriş başarılı. Token kaydedildi. userid="+userId;
        tokenDiv.style.color = "green";
        errorDiv.textContent = "";

        setTimeout(() => {
          window.location.href = 'Giriş/address.html';
        }, 2000);

      } else {
        errorDiv.textContent = "Kullanıcı adı veya şifre yanlış.";
        tokenDiv.textContent = "";
      }

    } catch (err) {
      document.getElementById('errorMsg').textContent = 'Bağlantı hatası: ' + err.message;
    }
  });