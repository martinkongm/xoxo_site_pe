const mp = new MercadoPago('TEST-e782734c-6a78-47ee-9400-8e6005244b1d', {
    locale: 'es-PE'
});

const MP = async () => {
    try {
        miArticulo = {};
        miArticulo.title = 'manzana';
        miArticulo.quantity = 1;
        miArticulo.price = 100;

        const response = await fetch("api/v1/mp", {
            method: 'POST',
            headers: {
                'Accept': 'Application/json',
                'Content-Type': 'Application/json'
            },
            body: JSON.stringify(miArticulo)
        })
        const preference = await response.text();
        createCheckoutButton(preference)
    } catch (e) {
        alert('error: ' + e);
    }}

const createCheckoutButton = (preferenceId_OK) => {
    const bricksBuilder = mp.bricks();
    const generateButton = async () => {
        if(window.checkoutButton) window.checkoutButton.unmount()
        bricksBuilder.create('wallet', 'wallet_container', {
            initialization: {
                preferenceId: preferenceId_OK,
            }
        })
    }
    generateButton()
}
