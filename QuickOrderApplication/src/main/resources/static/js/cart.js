class ShoppingCart {

  #contents = {}; // Key value store of selected products.

  constructor() {
    console.log("Constructing the cart...");
  }

  addCartEntry(productId, productSizeId, quantity, options) {
    productSizeId = '' + productSizeId;
    productId = '' + productId;

    let compositeId = productId + '_' + productSizeId;
    let mapping = {q: quantity, o:(options || [])};

    console.log("Saving -> " + compositeId + ": "  + JSON.stringify(mapping));
    this.#contents[compositeId] = mapping;
  }

  updateLink() {
    let keys = Object.keys(this.#contents);
    let number = 0;
    for (let i in keys) {
        number += parseInt(this.#contents[keys[i]].q, 10);
    }
    console.log("Displaying the cart, items: " + number);
    $('#cart-count').html(number > 0 ? number : '');
  }

  save() {
    console.log("Saving the cart...");
    window.sessionStorage.setItem('cart', JSON.stringify(this.#contents));
  }

  restore() {
    console.log("Restoring the cart...");
    let cartAsString = window.sessionStorage.getItem('cart') || '{}';
    this.#contents = JSON.parse(cartAsString);
  }

  restoreOptions() {
     $('input[type=checkbox]').prop("checked", false);
     $('input[type=checkbox]').parent().removeClass('chosen');

     let mainSizeSelector = 'input[name="productSizeChoice"]';
     let productId = this.getProductId();
     let productSizeId = $(mainSizeSelector + ':checked').val() ||
                              $(mainSizeSelector + ':first').val();
     let key = productId + '_' + productSizeId;
     let product = this.#contents[key];
     if (!product) {
        console.log('No entry for ' + key);
        return;
     }

     let sizeRadio = $('#sizeRadio' + productSizeId);
     sizeRadio.prop("checked", true);
     sizeRadio.parent().addClass('chosen');

     $('#item-qty').val(product.q);
     console.log('Restoring for ' + key + ": " + product.o + '...');
     for (let j = 0; j < product.o.length; j++) {
       let option = $('#optionCheck' + product.o[j]);
       option.prop("checked", true);
       option.parent().addClass('chosen');
     }
  }

  resetForm() {
    console.log("Resetting the form...");
    $('input[type=radio]').prop("checked", false);
    $('input[type=radio]').parent().removeClass('chosen');
    $('input[type=checkbox]').prop("checked", false);
    $('input[type=checkbox]').parent().removeClass('chosen');
    $('#item-qty').val(1);
  }

  addProduct() {
    let productId = this.getProductId();
    if (productId == 0) {
      console.log('Unable to get product id.');
      return;
    }
    let mainSizeSelector = 'input[name="productSizeChoice"]';
    let productSizeId = $(mainSizeSelector + ':checked').val() || 0;
    if (productSizeId == 0) {
       $('.product-size-label').effect("highlight", {}, 1200);
       console.log('Product size is not selected.');
       return;
    }

    let options = [];
    $('.choice input[type=checkbox]:checked').each(function() {
       options.push($(this).val());
    });

    let quantity = parseInt($('#item-qty').val(), 10);

    this.addCartEntry(productId, productSizeId, quantity, options);
    this.save();

    this.updateLink();
  }

  getProductId() {
    return this.getCurrentUrl().match(/item\/([0-9]+)/i)[1] || 0;
  }

  getCurrentUrl() {
    return window.location.href + '';
  }
}

const shoppingCart = new ShoppingCart();

$(document).ready(function() {
    shoppingCart.restore();
    shoppingCart.restoreOptions();
    shoppingCart.updateLink();

    $('input[name="productSizeChoice"]').change(function () {
        $('input[name="productSizeChoice"]').each(function(index, element) {
            $(element).parent().removeClass('chosen');
        });
        $(this).parent().addClass('chosen');
        shoppingCart.restoreOptions();
    });

    $('.choice input[type=checkbox]').change(function () {
        if (this.checked) {
            $(this).parent().addClass('chosen');
        } else {
            $(this).parent().removeClass('chosen');
        }
    });
});