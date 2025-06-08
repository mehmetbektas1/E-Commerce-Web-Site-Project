const express = require('express');
const fs = require('fs');
const cors = require('cors');
const app = express();
const PORT = 3000;

app.use(cors());
app.use(express.json());

// Kayıt olma
app.post('/kayit', (req, res) => {
  const { username, email, password } = req.body;
  if (!username || !email || !password) {
    return res.json({ success: false, message: "Tüm alanlar zorunlu!" });
  }
  let users = [];
  if (fs.existsSync('Kayıt/Kayıt.json')) {
    users = JSON.parse(fs.readFileSync('Kayıt/Kayıt.json'));
  }
  if (users.find(u => u.email === email)) {
    return res.json({ success: false, message: "Bu e-posta ile zaten kayıt olunmuş!" });
  }
  users.push({ username, email, password });
  fs.writeFileSync('Kayıt/Kayıt.json', JSON.stringify(users, null, 2));
  res.json({ success: true });
});

// Giriş kontrolü
app.post('/giris', (req, res) => {
  const { username, password } = req.body;
  if (!username || !password) {
    return res.json({ success: false, message: "Tüm alanlar zorunlu!" });
  }
  let users = [];
  if (fs.existsSync('Kayıt/Kayıt.json')) {
    users = JSON.parse(fs.readFileSync('Kayıt/Kayıt.json'));
  }
  const user = users.find(u => u.username === username && u.password === password);
  if (user) {
    res.json({ success: true });
  } else {
    res.json({ success: false, message: "Kullanıcı adı veya şifre hatalı!" });
  }
});

app.listen(PORT, () => {
  console.log(`Sunucu çalışıyor: http://localhost:${PORT}`);
});