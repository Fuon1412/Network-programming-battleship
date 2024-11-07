// main.js

const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("path");
const net = require("net");

const createWindow = () => {
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, "./src/preload.js"),
      contextIsolation: true,
      enableRemoteModule: false,
      nodeIntegration: false,
    },
  });

  mainWindow.loadFile("./src/index.html");
};

app.whenReady().then(() => {
  createWindow();

  app.on("activate", () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});

ipcMain.handle("login", async (event, username, password) => {
  return new Promise((resolve, reject) => {
    const client = new net.Socket();
    client.connect(8080, "127.0.0.1", () => {
      client.write(`LOGIN;${username};${password}\n`);
    });

    client.on("data", (data) => {
      resolve(data.toString());
      client.destroy();
    });

    client.on("error", (err) => {
      reject(err.message);
      client.destroy();
    });
  });
});

ipcMain.handle("register", async (event, name, username, password) => {
  return new Promise((resolve, reject) => {
    const client = new net.Socket();
    client.connect(8080, "127.0.0.1", () => {
      client.write(`REGISTER;${name};${username};${password}\n`);
    });

    client.on("data", (data) => {
      resolve(data.toString());
      client.destroy();
    });

    client.on("error", (err) => {
      reject(err.message);
      client.destroy();
    });
  });
});
