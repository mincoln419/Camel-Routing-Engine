async function sendAPI() {
  try {
    const url = document.getElementById("url").value;
    console.log(url);
    document.getElementById("responseMessage").textContent = "";
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const data = await response.json();
    console.log(data);
    const responseMessage = document.getElementById("responseMessage");
    const responseCnt = document.getElementById("responseCnt");
    responseMessage.textContent = JSON.stringify(data, null, 4);
    responseCnt.textContent = data.length;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

const urlMap = {
  itemAPI: "http://localhost:8083/api/items?limit=100&offset=0",

  factoryAPI: "http://localhost:8083/api/factories?limit=100&offset=0",

  itemDBlink: "http://localhost:8083/api/items/dblink?limit=100&offset=0",
};

const radioButtons = Array.from(document.querySelectorAll(".radio-option"));

console.log(radioButtons);

radioButtons.forEach((radio) => {
  let url = "";
  switch (radio.id) {
    case "itemByApi":
      url = urlMap.itemAPI;
      break;
    case "itemByDBlink":
      url = urlMap.itemDBlink;
      break;
    case "factoryByApi":
      url = urlMap.factoryAPI;
      break;
    default:
      url = urlMap.itemAPI;
      break;
  }
  radio.addEventListener("click", () => {
    document.getElementById("url").value = url;
  });
});

window.onload = () => document.getElementById("itemByApi").click();
