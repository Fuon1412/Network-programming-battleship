// renderer.js

$(function () {
  const login = async () => {
    const username = $("#username").val();
    const password = $("#password").val();

    try {
      const response = await window.api.login(username, password);
      alert(response);
      if (response.includes("success")) {
        $("#loginModal").modal("hide");
        alert("Login successful");
      }
    } catch (error) {
      alert("Login failed: " + error);
    }
  };

  const register = async () => {
    const fullName = $("#fullName").val();
    const username = $("#reg-username").val();
    const password = $("#reg-password").val();

    try {
      const response = await window.api.register(fullName, username, password);
      alert(response);
      if (response.includes("success")) {
        $("#registerModal").modal("hide");
        alert("Registration successful");
      }
    } catch (error) {
      alert("Registration failed: " + error);
    }
  };

  const connectToServer = async () => {
    try {
      const response = await window.api.connectToServer();
      alert(response);
    } catch (error) {
      alert("Failed to connect to server: " + error);
    }
  };

  const showGameMenu = () => {
    $("#initialScene").addClass("d-none");
    $("#gameMenuScene").removeClass("d-none");
  };

  const showInitialScene = () => {
    $("#gameMenuScene").addClass("d-none");
    $("#initialScene").removeClass("d-none");
  };

  $("#startButton").on("click", showGameMenu);
  $("#returnButton").on("click", showInitialScene);
  $("#exitButton").on("click", () => {
    window.api.exitApp();
  });

  $("#multiplayerButton").on("click", () => {
    connectToServer()
      .then(() => {
        $("#loginModal").modal("show");
      })
      .catch((error) => {
        alert(error);
      });
  });

  $("#modalLoginButton").on("click", login);
  $("#modalRegisterButton").on("click", register);
});
