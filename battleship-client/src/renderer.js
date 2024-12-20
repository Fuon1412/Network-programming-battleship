// renderer.js

$(function () {
  const login = async () => {
    const username = $("#username").val();
    const password = $("#password").val();

    try {
      const response = await window.api.login(username, password);
      alert(response);
      if (response.startsWith("login success")) {
        $("#loginModal").modal("hide");
        alert("Login successful");

        const playerInfo = response.split(" ");
        const playerName = playerInfo[2];
        const playerElo = playerInfo[3];

        localStorage.setItem("playerName", playerName);
        localStorage.setItem("playerUsername", username);
        localStorage.setItem("playerElo", playerElo);

        // Redirect to lobby
        window.location.href = "lobby.html";
      }
    } catch (error) {
      alert("Login failed: " + error);
    }
  };

  const register = async () => {
    const fullName = $("#fullName").val();
    const username = $("#regUsername").val();
    const password = $("#regPassword").val();

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

  // Event listeners for switching modals
  $("#showRegisterModal").on("click", function () {
    $("#loginModal").modal("hide");
    $("#registerModal").modal("show");
  });

  $("#showLoginModal").on("click", function () {
    $("#registerModal").modal("hide");
    $("#loginModal").modal("show");
  });
});
