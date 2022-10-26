/**
 * Renders the interactive canvas.
 * This code assumes that there is a global POINTS variable containing 
 * all the points to render.
 */

const canvas = document.getElementById("graph-canvas");
const ctx = canvas.getContext('2d');
const loadingImage = document.getElementById("loading-gif");
const themeColor = [0x83, 0x38, 0xec, 0xaa];

canvas.hidden = true;
loadingImage.hidden = false;

if (BITMAP !== undefined) {
    const bitmapBytes = atob(BITMAP);
    let bitmap = [];

    // Counts bitmap side length
    let s = 0;

    for (let i = 0; i < bitmapBytes.length; i++) {
        for (let j = 0; j < 8; j++) {
            bitmap.push((bitmapBytes.charCodeAt(i) >> (7 - j)) % 2);

            if (bitmap.length >= (s+1) * (s+1)) s++;
        }
    }

    bitmap = bitmap.slice(0, s*s);
    // Workaround to avoid painting image multiple times on canvas.
    // Due to glitch the backgorund dissappears on mouse move.
    areasImage = generateImageDataFromBitmap(ctx, bitmap, s);
    ctx.putImageData(areasImage, 0, 0);
    canvas.style = `background: url("${canvas.toDataURL()}")`;

    renderGraph();
    canvas.hidden = false;
    loadingImage.hidden = true;
}

function generateImageDataFromBitmap(ctx, bitmap, bitmapSize) {
    const w = canvas.width;
    const imageData = ctx.createImageData(w, w);

    for (let y = 0; y < w; y++) {
        for (let x = 0; x < w; x++) {
            const x_ = Math.floor(x * bitmapSize / w);
            const y_ = Math.floor(y * bitmapSize / w);

            if (bitmap[y_ * bitmapSize + x_]) {
                themeColor.forEach((v, c) => imageData.data[(y*w+x)*4+c] = v);
            }
        }
    }

    return imageData;
}

/**
 * @returns The currently selected or otherwise appropriate R, 0 if not available.
 */
function getR() {
    let r = 0;
    
    const form = document.getElementById('form');
    
    if (form) {
        const formData = new FormData(form);
        const parsedR = parseFloat(formData.get('form:r'));
        r = (isNaN(parsedR) ? 0 : parsedR);
    }

    return r;
}

/**
 * Re-renders the graph with points using currently selected R.
 */
function renderGraph() {
    const r = getR();

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    //ctx.putImageData(areasImage, 0, 0);

    const width = canvas.width;
    const height = canvas.height;

    ctx.strokeStyle = '#FFFFFFFF';
    ctx.fillStyle = '#FFFFFFFF';

    ctx.beginPath();
    ctx.moveTo(0, height/2);
    ctx.lineTo(width, height/2);
    ctx.lineTo(width-10, height/2-10);
    ctx.moveTo(width, height/2);
    ctx.lineTo(width-10,height/2+10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width/2, height);
    ctx.lineTo(width/2, 0);
    ctx.lineTo(width/2-10, 10);
    ctx.moveTo(width/2, 0);
    ctx.lineTo(width/2+10, 10);
    ctx.stroke();

    const labels = ['-R', '-R/2', '', 'R/2', 'R'];

    // Draw axes labels
    for (let i=1; i<6; i++) {
        ctx.beginPath();
        ctx.moveTo(i*width/6, height/2-5);
        ctx.lineTo(i*width/6, height/2+5);
        ctx.moveTo(width/2-5, i*height/6);
        ctx.lineTo(width/2+5, i*height/6);
        ctx.stroke();

        ctx.textAlign = 'center';
        ctx.textBaseline = 'bottom';
        ctx.fillText(labels[i-1], i*width/6, height/2-7);

        ctx.textAlign = 'left';
        ctx.textBaseline = 'middle';
        ctx.fillText(labels[i-1], width/2+7, height - i*height/6);
    }

    if (r === 0) return;

    // Draw all points
    points.forEach((v) => {
        const x = v.x / r * width / 3 + width / 2;
        const y = -v.y / r * height / 3 + height / 2;

        ctx.fillStyle = (v.success ? '#1e18ff' : '#dc3545');
        ctx.beginPath();
        ctx.arc(x, y, 5, 0, Math.PI * 2);
        ctx.fill();
    });
}

canvas.addEventListener('mousedown', (ev) => {
    const r = getR();
    if (r === 0) {
        alert('Please select R first');
        return;
    }

    const x = Math.round((ev.offsetX / canvas.width - 0.5) * 3 * r * 100) / 100;
    const y = Math.round((ev.offsetY / canvas.height - 0.5) * -3 * r * 100) / 100;

    document.getElementById('form:x').value = x.toString();
    document.getElementById('form:y').value = y.toString();
    console.log(document.getElementById('form:x').value);
    console.log(document.getElementById('form:y').value);
    // Apparently the button re-renders somehow, can't keep a global reference to the button.
    document.getElementById("form:submit-button").click();
});

canvas.addEventListener('mousemove', (ev) => {
    renderGraph();
    ctx.fillStyle = `rgb(${themeColor[0]}, ${themeColor[1]}, ${themeColor[2]})`;
    ctx.beginPath();
    ctx.arc(ev.offsetX, ev.offsetY, 5, 0, Math.PI * 2);
    ctx.fill();
});

canvas.addEventListener('mouseleave', renderGraph);