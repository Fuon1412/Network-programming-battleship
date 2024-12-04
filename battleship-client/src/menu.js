// renderer.js
$(function () {
  const login = async () => {
    const username = $("#username").val();
    const password = $("#password").val();

    try {
      const response = await window.api.login(username, password);
      if (response.startsWith(window.protocol.LOGIN_SUCCESS)) {
        $("#loginModal").modal("hide");
        alert("Login successful");

        const playerInfo = response.split(" ");
        const playerName = playerInfo[1];
        const playerElo = playerInfo[2];

        localStorage.setItem("playerName", playerName);
        localStorage.setItem("playerUsername", username);
        localStorage.setItem("playerElo", playerElo);

        // Redirect to lobby
        window.location.href = "lobby.html";
      }
      else
      {
        switch(response)
        {
          case window.protocol.INVALID_CREDENTIALS:
            alert("Invalid credentials");
            break;
          case window.protocol.ALREADY_LOGGED_IN:
            alert("Already logged in");
            break;
          default:
            alert("Login failed: " + response);
            break;
        }
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
      if (response.includes(window.protocol.REGISTER_SUCCESS)) {
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
