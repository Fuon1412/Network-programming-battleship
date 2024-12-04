$(function () {
  const playerName = localStorage.getItem("playerName");
  const playerUsername = localStorage.getItem("playerUsername");
  const playerElo = localStorage.getItem("playerElo");

  document.getElementById("playerName").textContent = playerName || "N/A";
  document.getElementById("playerUsername").textContent =
    playerUsername || "N/A";
  document.getElementById("playerElo").textContent = playerElo || "N/A";

  let refreshInterval; // Declare refreshInterval here

  const loadOnlinePlayers = async () => {
    try {
      const onlinePlayers = await window.api.onlinePlayers(); // Fetch online players
      const playerList = document.getElementById("playerList");
      playerList.innerHTML = ""; // Clear existing list

      onlinePlayers.forEach((player) => {
        const li = document.createElement("li");
        li.className = "list-group-item";
        li.textContent = `${player.name} (${player.elo})`;
        playerList.appendChild(li);
      });
    } catch (error) {
      console.error("Failed to load online players:", error);
    }
  };

  // Load online players on page load
  loadOnlinePlayers();

  // Function to start refreshing the player list
  const startRefreshing = () => {
    // If there's an existing interval, clear it before starting a new one
    if (refreshInterval) {
      clearInterval(refreshInterval);
    }

    // Start a new interval to refresh the online players list every 5 seconds
    refreshInterval = setInterval(loadOnlinePlayers, 5000);
  };

  // Start refreshing when the page loads
  startRefreshing();

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

      // Clear the interval when logging out to prevent it from continuing to run in the background
      if (refreshInterval) {
        clearInterval(refreshInterval);
      }
    } catch (error) {
      alert("Logout failed: " + error);
    }
  };

  $("#logoutButton").on("click", logout);

  // Open Room Modal when Room button is clicked
  $("#roomButton").on("click", () => {
    $("#roomModal").modal("show");
  });

  // Handle Room actions
  $("#createRoomButton").on("click", async () => {
    try {
      const response = await window.api.createRoom(playerUsername);
      alert(response.message || "Room created successfully!");
    } catch (error) {
      alert("Failed to create room: " + error);
    }
  });

  $("#joinRoomButton").on("click", async () => {
    try {
      const response = await window.api.joinRoom(playerUsername);
      alert(response.message || "Joined room successfully!");
    } catch (error) {
      alert("Failed to join room: " + error);
    }
  });

  $("#exitRoomButton").on("click", async () => {
    try {
      const response = await window.api.exitRoom(playerUsername);
      alert(response.message || "Exited room successfully!");
    } catch (error) {
      alert("Failed to exit room: " + error);
    }
  });
});
