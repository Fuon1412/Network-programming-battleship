// preload.js

const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('api', {
    register: (name, username, password) => ipcRenderer.invoke('register', name, username, password),
    login: (username, password) => ipcRenderer.invoke('login', username, password),
    connectToServer: () => ipcRenderer.invoke('connect-to-server'),
    exitApp: () => ipcRenderer.send('exit-app')
});
