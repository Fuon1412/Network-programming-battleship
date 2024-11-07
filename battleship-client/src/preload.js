// preload.js

const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('api', {
    login: (username, password) => ipcRenderer.invoke('login', username, password),
    register: (name, username, password) => ipcRenderer.invoke('register', name, username, password)
});
