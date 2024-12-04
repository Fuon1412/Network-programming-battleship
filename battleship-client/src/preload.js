// preload.js
const { contextBridge, ipcRenderer } = require("electron");

contextBridge.exposeInMainWorld("api", {
  connectToServer: () => ipcRenderer.invoke("connect-to-server"),
  //Api lien quan den viec dang nhap va dang ky
  login: (username, password) =>
    ipcRenderer.invoke("login", username, password),
  register: (name, username, password) =>
    ipcRenderer.invoke("register", name, username, password),
  logout: (username) => ipcRenderer.invoke("logout", username),
  exitApp: () => ipcRenderer.send("exit-app"),
});

contextBridge.exposeInMainWorld("protocol", {
  LOGIN: process.env.LOGIN,
  REGISTER: process.env.REGISTER,
  LOGOUT: process.env.LOGOUT,
  EXIT: process.env.EXIT,

  CREATE_ROOM: process.env.CREATE_ROOM,
  GET_ROOM_LIST: process.env.GET_ROOM_LIST,
  JOIN_ROOM: process.env.JOIN_ROOM,
  READY: process.env.READY,
  LEAVE_ROOM: process.env.LEAVE_ROOM,
  START_GAME: process.env.START_GAME,
  INIT_BOARD: process.env.INIT_BOARD,
  PLACE_SHIP: process.env.PLACE_SHIP,
  SURRENDER: process.env.SURRENDER,
  MOVE_SHIP: process.env.MOVE_SHIP,
  SHOOT: process.env.SHOOT,
  CHAT: process.env.CHAT,

  LOGIN_SUCCESS: process.env.LOGIN_SUCCESS,
  REGISTER_SUCCESS: process.env.REGISTER_SUCCESS,
  LOGOUT_SUCCESS: process.env.LOGOUT_SUCCESS,
  INIT_SUCCESS: process.env.INIT_SUCCESS,
  CREATE_ROOM_SUCCESS: process.env.CREATE_ROOM_SUCCESS,
  JOIN_ROOM_SUCCESS: process.env.JOIN_ROOM_SUCCESS,
  REMOVE_ROOM_SUCCESS: process.env.REMOVE_ROOM_SUCCESS,
  LEAVE_ROOM_SUCCESS: process.env.LEAVE_ROOM_SUCCESS,

  LOGIN_FAIL: process.env.LOGIN_FAIL,
  REGISTER_FAIL: process.env.REGISTER_FAIL,
  LOGOUT_FAIL: process.env.LOGOUT_FAIL,
  NOT_FOUND: process.env.NOT_FOUND,
  ALREADY_LOGGED_IN: process.env.ALREADY_LOGGED_IN,
  INVALID_CREDENTIALS: process.env.INVALID_CREDENTIALS,
  INVALID_PARAMETERS: process.env.INVALID_PARAMETERS,
  INVALID_COMMAND: process.env.INVALID_COMMAND,

  UNKNOWN_ERROR: process.env.UNKNOWN_ERROR,
  INVALID_SIZE: process.env.INVALID_SIZE,
  FULL_ROOM: process.env.FULL_ROOM,
  JOIN_ROOM_FAIL: process.env.JOIN_ROOM_FAIL,
});
