// renderer.js

const register = async () => {
    const name = document.getElementById('reg-name').value;
    const username = document.getElementById('reg-username').value;
    const password = document.getElementById('reg-password').value;
    
    const response = await window.api.register(name, username, password);
    document.getElementById('reg-response').innerText = response;
};

const login = async () => {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;
    
    const response = await window.api.login(username, password);
    document.getElementById('login-response').innerText = response;
};
