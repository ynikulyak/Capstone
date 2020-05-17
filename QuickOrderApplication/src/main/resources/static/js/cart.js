class ShoppingCart {

  #contents = [];

  constructor() {
    console.log("Constructing the cart...");
  }

  addProductSize(productSizeId) {
  }

  addOption(productSizeId, optionId) {
  }

  updateLink() {
    let number = this.#contents.length;
    console.log("Displaying the cart, items: " + number);
    $('#cart-count').html(number > 0 ? number : '');
  }

  save() {
    console.log("Saving the cart...");
    window.sessionStorage.setItem('cart', JSON.stringify(this.#contents));
  }

  restore() {
    console.log("Restoring the cart...");
    let cartAsString = window.sessionStorage.getItem('card') || '[]';
    this.#contents = JSON.parse(cartAsString);
  }
}

const shoppingCart = new ShoppingCart();

$(document).ready(function() {
    shoppingCart.restore();
    shoppingCart.updateLink();
});