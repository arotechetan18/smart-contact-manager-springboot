
console.log("SCRIPT LOADED");


document.addEventListener("DOMContentLoaded", () => {

  // get theme from localStorage
  let currentTheme = getTheme();

  // apply theme on page load
  applyTheme(currentTheme);

  // theme change button
  const changeThemeButton = document.querySelector("#theme_change_button");

  // safety check (avoid null error)
  if (!changeThemeButton) {
    console.error("Theme change button not found");
    return;
  }

  // click listener
  changeThemeButton.addEventListener("click", () => {

    const oldTheme = currentTheme;

    // toggle theme
    currentTheme = currentTheme === "dark" ? "light" : "dark";

    // save to localStorage
    setTheme(currentTheme);

    // update html class
    document.documentElement.classList.remove(oldTheme);
    document.documentElement.classList.add(currentTheme);

    // update button text
    changeThemeButton.querySelector("span").textContent =
      currentTheme === "light" ? "Dark" : "Light";
  });
});


// apply theme to html tag
function applyTheme(theme) {
  document.documentElement.classList.add(theme);
}


//save theme
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}


//  get theme
function getTheme() {
  return localStorage.getItem("theme") || "light";
}


