$(function () {
  const playerName = localStorage.getItem("playerName");
  const playerUsername = localStorage.getItem("playerUsername");
  const playerElo = localStorage.getItem("playerElo");

  document.getElementById("playerName").textContent = playerName || "N/A";
  document.getElementById("playerUsername").textContent =
    playerUsername || "N/A";
  document.getElementById("playerElo").textContent = playerElo || "N/A";

  const logout = async () => {
    try {
      const response = await window.api.logout(playerUsername);
      if (response.startsWith(window.protocol.LOGOUT_SUCCESS)) {
        localStorage.removeItem("playerName");
        localStorage.removeItem("playerUsername");
        localStorage.removeItem("playerElo");
        alert("Logout successful");
      }

      window.location.href = "main.html";
    } catch (error) {
      alert("Logout failed: " + error);
    }
  };

  $("#logoutButton").on("click", logout);
});
