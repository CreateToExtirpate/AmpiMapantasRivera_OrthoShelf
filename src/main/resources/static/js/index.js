document //Borrowable Instruments Tab
  .getElementById("borrowable-tab")
  .addEventListener("click", function () {
    fetch("/index/borrowable/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.borrowType}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("borrowable-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Borrowable Instruments:", error)
      );
  });

document //Consumable Instruments Tab
  .getElementById("consumable-tab")
  .addEventListener("click", function () {
    fetch("/index/consumable/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 50
              ? "badge-high"
              : instrument.stock > 30
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.consumableType}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id},'CONSUMABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})"">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("consumable-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Consumable Instruments:", error)
      );
  });

document //Cutting Instruments Tab
  .getElementById("cutting-tab")
  .addEventListener("click", function () {
    fetch("/index/cutting/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.cuttingType}</td>` +
            `<td>${instrument.instrumentType}</td>` +
            `<td>${instrument.instrumentSize}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("cutting-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Cutting Instruments:", error)
      );
  });

document //Grasping Instruments Tab
  .getElementById("grasping-tab")
  .addEventListener("click", function () {
    fetch("/index/grasping/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.graspingType}</td>` +
            `<td>${instrument.instrumentSize}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("grasping-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Grasping Instruments:", error)
      );
  });

document //Retractor Instruments Tab
  .getElementById("retractor-tab")
  .addEventListener("click", function () {
    fetch("/index/retractor/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.retractorType}</td>` +
            `<td>${instrument.instrumentType}</td>` +
            `<td>${instrument.instrumentSize}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("retractor-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Retractor Instruments:", error)
      );
  });

document //Impacting Instruments Tab
  .getElementById("impacting-tab")
  .addEventListener("click", function () {
    fetch("/index/impacting/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.impactingType}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("impacting-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Impacting Instruments:", error)
      );
  });

document //Wire Instruments Tab
  .getElementById("wire-tab")
  .addEventListener("click", function () {
    fetch("/index/wire/data")
      .then((response) => response.json())
      .then((data) => {
        let html = "";
        data.forEach((instrument) => {
          let stockClass =
            instrument.stock > 10
              ? "badge-high"
              : instrument.stock > 5
              ? "badge-medium"
              : "badge-low";

          html +=
            `<tr>` +
            `<td>${instrument.id}</td>` +
            `<td>${instrument.name}</td>` +
            `<td>${instrument.wireType}</td>` +
            `<td>₱${instrument.price.toFixed(2)}</td>` +
            `<td><span class="badge-stock ${stockClass}">${instrument.stock}</span></td>` +
            `<td>${instrument.borrowCount}</td>` +
            `<td>${instrument.stock - instrument.borrowCount}</td>` +
            `<td style="white-space:nowrap">` +
            `<a class="btn btn-action btn-edit" data-bs-toggle="modal" data-bs-target="#instrumentModal" onclick="editInstrument(${instrument.id}, 'BORROWABLE')">Edit</a>` +
            `<a class="btn btn-action btn-delete" onclick="askConfirmationDelete(${instrument.id})">Delete</a>` +
            `</td>` +
            `</tr>`;
        });
        document.getElementById("wire-content").innerHTML = html;
      })
      .catch((error) =>
        console.error("Error loading Wire Instruments:", error)
      );
  });

/*ALL LOGIC HERE WILL HANDLE THE MODAL*/

let currentTab = 0;
let modalName;
let tabs;
let isGauzePad = false;

function showTab(n, modalName) {
  tabs = document.getElementsByClassName(modalName);
  tabs[n].style.display = "block";

  //Handle showing of back button
  if (n == 0) {
    document.getElementById("back-btn").style.display = "none";
  } else {
    document.getElementById("back-btn").style.display = "block";
  }

  //Handle the Submit Button
  if (n == tabs.length - 1) {
    document.getElementById("next-btn").innerHTML = "Submit";
  } else {
    document.getElementById("next-btn").innerHTML = "Next";
  }
}

function nextPrev(n) {
  //Will not accept missing inputs
  if (n == 1 && !validateForm()) {
    alert("Please fill all missing fields.");
    return false;
  }

  tabs[currentTab].style.display = "none";
  currentTab = currentTab + n;

  if (currentTab >= tabs.length) {
    switch (modalName) {
      case "add-tab":
        askConfirmationAdd();
        break;
      case "edit-tab":
        askConfirmationEdit();
        break;
    }
    currentTab = 0;
  }

  showTab(currentTab, modalName);
}

function validateForm() {
  let tabs = Array.from(document.getElementsByClassName(modalName));
  let valid = true;

  tabs[currentTab]
    .querySelectorAll('input[type="text"], input[type="number"]')
    .forEach((input) => {
      if (input.value.trim() === "") {
        if (!input.classList.contains("invalid")) {
          input.classList.add("invalid");
        }
        valid = false;
      } else {
        if (input.classList.contains("invalid")) {
          input.classList.remove("invalid");
        }
      }
    });

  let radioGroups = new Set();
  tabs[currentTab].querySelectorAll('input[type="radio"]').forEach((radio) => {
    radioGroups.add(radio.name);
  });

  radioGroups.forEach((groupName) => {
    let group = tabs[currentTab].querySelectorAll(`input[name="${groupName}"]`);
    let isChecked = Array.from(group).some((r) => r.checked);
    group.forEach((radio) => {
      if (!isChecked) {
        if (!radio.classList.contains("invalid")) {
          radio.classList.add("invalid");
        }
        valid = false;
      } else {
        if (radio.classList.contains("invalid")) {
          radio.classList.remove("invalid");
        }
      }
    });
  });

  return valid;
}

//Clear all inputs on all tabs
function clearInputs() {
  
  let tabs = Array.from(document.querySelectorAll('.modal-body > div'));
  tabs.forEach((tab) => {
    //hide tabs away
    tab.style.display = "none";

    //clear text & numbers
    tab
      .querySelectorAll('input[type="text"], input[type="number"]')
      .forEach((input) => {
        input.value = "";
      });

    //clear radios
    tab.querySelectorAll('input[type="radio"]').forEach((radio) => {
      radio.checked = false;
    });

    //Clear textarea
    tab.querySelectorAll("textarea").forEach((textarea) => {
      textarea.value = "";
    });

    //remove invalids
    tab
      .querySelectorAll('input[type="text"], input[type="number"]')
      .forEach((input) => {
        if (input.classList.contains("invalid")) {
          input.classList.remove("invalid");
        }
      });

    tab.querySelectorAll('input[type="radio"]').forEach((radio) => {
      if (radio.classList.contains("invalid")) {
        radio.classList.remove("invalid");
      }
    });
  });

  if (modalName == "add-tab") {
    hideDivs(
      document.getElementById("sub-types-section").querySelectorAll("div")
    );
    hideDivs(document.getElementById("use-type-box").querySelectorAll("div"));
    hideDivs(document.getElementById("sizes-box").querySelectorAll("div"));
  }
}
/*DELETE INSTRUMENT*/
function deleteFromAll(elem) {
  const id = elem.getAttribute("data-del");
  askConfirmationDelete(id);
}

function askConfirmationDelete(id) {
  if (confirm("Are you sure you want to delete this instrument?")) {
    fetch(`/api/instrument/delete/${id}`, {
      method: "DELETE"
    })
    .then((response) => {
        if (response.ok) {
          alert("Instrument deleted successfully!");
          location.reload();
        } else {
          alert("Failed to delete instrument.");
        }
      })
      .catch((error) => console.error("Error deleting instrument:", error));
  }
}

/*EDIT INSTRUMENT*/
function editInstrumentFromAll(elem) {
  const id = elem.getAttribute("data-id");
  const category = elem.getAttribute("data-cat");
  editInstrument(id, category);
}

function editInstrument(id, category) {
  currentTab = 0;
  modalName = "edit-tab";
  clearInputs();
  showTab(currentTab, modalName);
  document.getElementById("instrumentModalLabel").innerHTML = "Edit Instrument";

  fetch(`/api/instrument/edit?id=${id}&category=${category}`)
    .then((response) => response.json())
    .then((data) => {
      document.getElementById("read_id").value = data.id;
      document.getElementById("edit_name").value = data.name;
      document.getElementById("edit_price").value = data.price;
      document.getElementById("edit_stock").value = data.stock;

      document.getElementById("read_borrowedStock").value = data.borrowedStock;
      document.getElementById("edit_desc").value = data.description;
      document.getElementById("read_category").value = data.category;
      document.getElementById("read_type").value = data.type;
      document.getElementById("read_subtype").value = data.subtype;
      document.getElementById("read_usetype").value = data.usetype;
      document.getElementById("read_size").value = data.size;
    })
    .catch((error) => console.error("Error fetching instrument data: ", error));
}

function askConfirmationEdit() {
  if (confirm("Are you sure you want to update this instrument?")) {
    const id = document.getElementById("read_id").value;
    const formData = new FormData();

    formData.append("name", document.getElementById("edit_name").value);
    formData.append("price", document.getElementById("edit_price").value);
    formData.append("description", document.getElementById("edit_desc").value);
    formData.append("category", document.getElementById("read_category").value);

    let stock = document.getElementById("edit_stock").value;
    let borrowed = document.getElementById("read_borrowedStock").value;
    if (stock < borrowed) {
      alert("Stock cannot be lower than borrowed amount.");
      return;
    } else {
      formData.append("stock", document.getElementById("edit_stock").value);
    }

    fetch(`/api/instrument/edit/${id}`, {
      method: "PUT",
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          alert("Instrument updated successfully!");
          location.reload();
        } else {
          alert("Failed to update instrument.");
        }
      })
      .catch((error) => console.error("Error updating instrument:", error));
  }
}

/*ADD INSTRUMENTS*/
let add_name;
let add_price;
let add_stock;
let add_desc;
let add_category;
let add_type;
let add_subtype;
let add_usetype;
let add_size;

function addNewInstrument() {
  const formData = new FormData();

  formData.append("name", add_name);
  formData.append("price", add_price);
  formData.append("stock", add_stock);
  formData.append("description", add_desc);
  formData.append("category", add_category);
  formData.append("type", add_type);
  formData.append("subtype", add_subtype);
  formData.append("usetype", add_usetype);
  formData.append("size", add_size);

  fetch("/api/instrument/create", {
    method: "POST",
    body: formData,
  })
    .then((response) => {
      if (response.ok) {
        alert("Instrument created successfully!");
      } else {
        alert("Failed to create instrument.");
      }
      location.reload();
    })
    .catch((error) => console.error("Error in submitting: ", error));
}

function addInstrumentModal() {
  currentTab = 0;
  modalName = "add-tab";
  clearInputs();
  showTab(currentTab, modalName);
  document.getElementById("instrumentModalLabel").innerHTML =
    "Add New Instrument";
  document.getElementById("type-options").style.display = "none";
}

function askConfirmationAdd() {
  if (confirm("Are you sure you want to add this instrument?")) {
    add_name = document.getElementById("instrument-name").value;
    add_price = document.getElementById("instrument-price").value;
    add_stock = document.getElementById("instrument-stock").value;
    add_desc = document.getElementById("instrument-desc").value;
    add_category = document.querySelector(
      'input[name="instrument_category"]:checked'
    ).value;
    add_type = document.querySelector(
      'input[name="instrument_type"]:checked'
    ).value;
    add_subtype = document.querySelector(
      'input[name="instrument_subtype"]:checked'
    ).value;
    add_usetype = document.querySelector(
      'input[name="instrument_usetype"]:checked'
    ).value;
    add_size = document.querySelector(
      'input[name="instrument_size"]:checked'
    ).value;

    addNewInstrument();
  }
}

function hideTypeOptions() {
  document.getElementById("type-options").style.display = "none";
  var radios = document.querySelectorAll('.types-section input[type="radio"]');
  radios.forEach((radio) => {
    radio.checked = false;
  });
}

function typeOptions() {
  let type = document.querySelector(
    'input[name="instrument_category"]:checked'
  ).value;

  let borrow = document.getElementById("borrowable-types");
  let consume = document.getElementById("consumable-types");

  document.getElementById("type-options").style.display = "block";
  if (type == "BORROWABLE") {
    borrow.style.display = "block";
    consume.style.display = "none";
    let radios = consume.querySelectorAll('input[type="radio"]');
    radios.forEach((radio) => (radio.checked = false));
  } else {
    consume.style.display = "block";
    borrow.style.display = "none";
    let radios = borrow.querySelectorAll('input[type="radio"]');
    radios.forEach((radio) => (radio.checked = false));
  }

  let tabs = document.getElementsByClassName(modalName);

  tabs[currentTab + 1]
    .querySelectorAll('input[type="radio"]')
    .forEach((radio) => {
      radio.checked = false;
    });
}

function subTypeOptions(type) {
  //Clear tab 2 inputs (for when instrument type is changed).
  clearTab2Inputs();

  //Specific conditon for gauzepads
  isGauzePad = false;
  if (type === "no-gauzepad") {
    isGauzePad = true;
    document.getElementById("no-subtypes").classList.toggle("hide-me");
    return;
  }

  let divs = document
    .getElementById("sub-types-section")
    .querySelectorAll("div");
  divs.forEach((div) => div.classList.add("hide-me"));

  const target = document.querySelector(`#sub-types-section #${type}-subtypes`);
  if (target) {
    target.classList.toggle("hide-me");
  } else {
    divs[5].classList.toggle("hide-me");
  }
}

function useTypeOptions(subType) {
  Array.from(
    document
      .getElementById("use-type-box")
      .querySelectorAll('input[type="radio"]')
  ).forEach((radio) => {
    radio.checked = false;
  });
  Array.from(
    document.getElementById("sizes-box").querySelectorAll('input[type="radio"]')
  ).forEach((radio) => {
    radio.checked = false;
  });

  let useDivs = document.getElementById("use-type-box").querySelectorAll("div");
  hideDivs(useDivs);

  let sizeDivs = document.getElementById("sizes-box").querySelectorAll("div");
  hideDivs(sizeDivs);

  switch (subType) {
    case "scissor":
      useDivs[4].classList.toggle("hide-me");
      useDivs[5].classList.toggle("hide-me");
      sizeDivs[5].classList.toggle("hide-me");
      break;
    case "chisel":
      useDivs[0].classList.toggle("hide-me");
      useDivs[1].classList.toggle("hide-me");
      sizeDivs[5].classList.toggle("hide-me");
      break;
    case "curette":
      useDivs[8].classList.toggle("hide-me");
      sizeDivs[0].classList.toggle("hide-me");
      sizeDivs[1].classList.toggle("hide-me");
      sizeDivs[2].classList.toggle("hide-me");
      break;
    case "curve":
      useDivs[8].classList.toggle("hide-me");
      sizeDivs[0].classList.toggle("hide-me");
      sizeDivs[1].classList.toggle("hide-me");
      sizeDivs[2].classList.toggle("hide-me");
      break;
    case "elevator":
      useDivs[8].classList.toggle("hide-me");
      sizeDivs[0].classList.toggle("hide-me");
      sizeDivs[2].classList.toggle("hide-me");
      break;
    case "angle":
      useDivs[0].classList.toggle("hide-me");
      useDivs[1].classList.toggle("hide-me");
      useDivs[2].classList.toggle("hide-me");
      useDivs[3].classList.toggle("hide-me");
      sizeDivs[0].classList.toggle("hide-me");
      sizeDivs[1].classList.toggle("hide-me");
      sizeDivs[2].classList.toggle("hide-me");
      break;
    case "self":
      useDivs[6].classList.toggle("hide-me");
      sizeDivs[5].classList.toggle("hide-me");
      break;
    default:
      if (isGauzePad) {
        useDivs[8].classList.toggle("hide-me");
        sizeDivs[0].classList.toggle("hide-me");
        sizeDivs[2].classList.toggle("hide-me");
        sizeDivs[3].classList.toggle("hide-me");
        sizeDivs[4].classList.toggle("hide-me");
        break;
      }
      useDivs[8].classList.toggle("hide-me");
      sizeDivs[5].classList.toggle("hide-me");
  }
}

function hideDivs(divs) {
  divs.forEach((div) => div.classList.add("hide-me"));
}

//Only clear sub-type, use-type, and size
function clearTab2Inputs() {
  hideDivs(
    document.getElementById("sub-types-section").querySelectorAll("div")
  );
  hideDivs(document.getElementById("use-type-box").querySelectorAll("div"));
  hideDivs(document.getElementById("sizes-box").querySelectorAll("div"));

  tabs = document.getElementsByClassName(modalName);
  tabs[currentTab + 1]
    .querySelectorAll('input[type="radio"]')
    .forEach((radio) => {
      radio.checked = false;
    });
}
