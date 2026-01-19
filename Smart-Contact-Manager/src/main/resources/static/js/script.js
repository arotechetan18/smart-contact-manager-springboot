let currentTheme = getTheme();
changeTheme(currentTheme);

function changeTheme(currentTheme) {
  console.log(currentTheme);
  //set the webpage
  document.addEventListener('DOMContentLoaded',()=>{
    
  })
  document.querySelector("html").classList.add(currentTheme);

  //listener
  const changeThemeButton = document.querySelector("#theme_change_button");
  changeThemeButton.addEventListener("click", (event) => {
    const oldTheme = currentTheme;
    console.log("buton click for theme change");
    //remove curernt theme
    document.querySelector("html").classList.remove(currentTheme);
    if (currentTheme === "dark") {
      //light karaychi ahe theme
      currentTheme = "light";
    } else {
      //dark karaychi ahe theme
      currentTheme = "dark";
    }
    setTheme(currentTheme);
    //remove curernt theme
    document.querySelector("html").classList.remove(oldTheme);

    document.querySelector("html").classList.add(currentTheme);
    //change theme buttton name
    changeThemeButton.querySelector("span").textContent =
      currentTheme == "light" ? "dark" : "light";
  });
}

//set theme to localstorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

//get theme from localstorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}
