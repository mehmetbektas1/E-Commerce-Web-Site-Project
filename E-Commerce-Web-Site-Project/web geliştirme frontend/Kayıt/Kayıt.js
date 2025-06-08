document.getElementById('registerForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const messageDiv = document.getElementById('message');

    if (!username || !email || !password || !confirmPassword) {
        messageDiv.innerHTML = '<div class="error">Tüm alanları doldurun!</div>';
        return;
    }
    if (password !== confirmPassword) {
        messageDiv.innerHTML = '<div class="error">Şifreler eşleşmiyor!</div>';
        return;
    }
    if (password.length < 6) {
        messageDiv.innerHTML = '<div class="error">Şifre en az 6 karakter olmalı!</div>';
        return;
    }

    fetch('http://localhost:3000/kayit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password })
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            messageDiv.innerHTML = '<div class="success">Kayıt başarılı!</div>';
            document.getElementById('registerForm').reset();
        } else {
            messageDiv.innerHTML = '<div class="error">' + data.message + '</div>';
        }
    })
    .catch(() => {
        messageDiv.innerHTML = '<div class="error">Sunucuya bağlanılamadı!</div>';
    });
});