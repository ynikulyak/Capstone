class ShoppingCart {

  #contents = {}; // Key value store of selected products.

  constructor() {
    console.log("Constructing the cart...");
  }

  addProductSize(productSizeId) {
    productSizeId = '' + productSizeId;
    let existing = this.#contents[productSizeId] || {q: 1, o:[]}; // quantity and options list
    existing['q'] = existing['q'] + 1;
    this.#contents[productSizeId] = existing;
  }

  addOption(productSizeId, optionId) {
  }

  updateLink() {
    let number = Object.keys(this.#contents).length;
    console.log("Displaying the cart, items: " + number);
    $('#cart-count').html(number > 0 ? number : '');
  }

  save() {
    console.log("Saving the cart...");
    window.sessionStorage.setItem('cart', JSON.stringify(this.#contents));
  }

  restore() {
    console.log("Restoring the cart...");
    let cartAsString = window.sessionStorage.getItem('card') || '{}';
    this.#contents = JSON.parse(cartAsString);
  }
}

const shoppingCart = new ShoppingCart();

$(document).ready(function() {
    shoppingCart.restore();
    shoppingCart.updateLink();
});