const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("path");
const net = require("net");
require("dotenv").config();

let client; // Persistent client socket

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

  mainWindow.loadFile("./views/main.html");
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

ipcMain.handle("connect-to-server", () => {
  return new Promise((resolve, reject) => {
    client = new net.Socket();
    client.connect(8080, "127.0.0.1", () => {
      resolve("Connected to server");
    });

    client.on("error", (err) => {
      reject("Failed to connect to server: " + err.message);
    });
  });
});

ipcMain.handle("login", async (event, username, password) => {
  return new Promise((resolve, reject) => {
    if (!client) {
      reject("Not connected to server");
      return;
    }
    client.write(`${process.env.LOGIN};${username};${password}\n`);
    client.once("data", (data) => {
      resolve(data.toString());
    });

    client.on("error", (err) => {
      reject(err.message);
    });
  });
});

ipcMain.handle("register", async (event, name, username, password) => {
  return new Promise((resolve, reject) => {
    if (!client) {
      reject("Not connected to server");
      return;
    }
    client.write(`${process.env.REGISTER};${name};${username};${password}\n`);
    client.once("data", (data) => {
      resolve(data.toString());
    });

    client.on("error", (err) => {
      reject(err.message);
    });
  });
});

ipcMain.handle("logout", async (event,username) => {
  return new Promise((resolve, reject) => {
    if (!client) {
      reject("Not connected to server");
      return;
    }
    client.write(`${process.env.LOGOUT};${username}\n`);
    client.once("data", (data) => {
      resolve(data.toString());
    });

    client.on("error", (err) => {
      reject(err.message);
    });
  });
});

ipcMain.on("exit-app", () => {
  if (client) {
    client.write(`${process.env.EXIT}\n`);
    client.end();
    client.destroy();
  }
  app.quit();
});

ipcMain.handle("online-players", async (event) => {
  return new Promise((resolve, reject) => {
    if (!client) {
      reject("Not connected to server");
      return;
    }
    client.write(`${process.env.ONLINE}\n`);
    client.once("data", (data) => {
      resolve(JSON.parse(data));
    });

    client.on("error", (err) => {
      reject(err.message);
    });
  });
});
