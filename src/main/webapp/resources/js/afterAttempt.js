function afterAttempt(data) {
    if (data.status === 'success') {
        const els = document.getElementsByClassName('timestamp');

        for (let i = 0; i < els.length; i++) {
            const el = els.item(i);
            const timestamp = parseInt(el.innerHTML);
            if (!isNaN(timestamp)) {
                el.innerHTML = new Date(timestamp).toLocaleString();
            }
        }
    }
}