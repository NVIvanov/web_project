function createComponent(data) {
    switch (data.type) {
        case 'BUTTON':
            return button(data);
        case 'IMAGE':
            return image(data);
        case 'PANEL':
            return panel(data);
        case 'TEXT':
            return text(data);
        case 'TEXTFIELD':
            return textField(data);
    }
}

function button(data) {
    const button = <button id={data.id}>{data.text}</button>;

    const styleString = createStyleString(data);
    if (!styleString.empty()) button.setAttribute('style', styleString);

    const listeners = createJsEventListeners(data);
    for (let event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            button.setAttribute(event, listeners[event]);
        }
    }

    return button;
}

function image(data) {
    const image = <img src={data.src}/>;

    const style = createStyleString(data);
    if (!style.empty()) image.setAttribute('style', style);

    const listeners = createJsEventListeners(data);
    for (let event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            image.setAttribute(event, listeners[event]);
        }
    }

    return image;
}

function panel(data) {
    const panel = <section/>;

    const style = createStyleString(data);
    if (!style.empty()) panel.setAttribute('style', style);

    const children = createChildren(data);
    for (let i = 0; i < children.length; i++) {
        panel.appendChild(children[i]);
    }
    return panel;
}

function text(data) {
    const paragraph = <p>{data['text']}</p>;

    const style = createStyleString(data);
    if (!style.empty()) paragraph.setAttribute('style', style);

    return paragraph;
}

function textField(data) {
    const field = <input type="text"/>;

    const style = createStyleString(data);
    if (!style.empty()) field.setAttribute('style', style);

    return field;
}

function createStyleString(data) {
    let style = '';
    if (data['width'] != null) {
        style += 'width: ' + data['width'];
    }
    if (data['height'] != null) {
        if (!style.empty()) style += '; ';
        style += 'height: ' + data['height'];
    }
    if (data['margin'] != null) {
        if (!style.empty()) style += '; ';
        style += 'margin: ' + data['margin'];
    }
    if (data['padding'] != null) {
        if (!style.empty()) style += '; ';
        style += 'padding: ' + data['padding'];
    }
    if (data['position'] != null) {
        if (!style.empty()) style += '; ';
        style += 'position: ' + data['position'];
    }
    if (data['fontSize'] != null) {
        if (!style.empty()) style += '; ';
        style += 'font-size: ' + data['fontSize'];
    }
    if (data['fontFamily'] != null) {
        if (!style.empty()) style += '; ';
        style += 'font-family: ' + data['fontFamily'];
    }
    if (data['colorHex'] != null) {
        if (!style.empty()) style += '; ';
        style += 'color: #' + data['colorHex'];
    }
    return style;
}

function createJsEventListeners(data) {
    let listeners = {};
    for (let listener in data['jsEventListeners']) {
        if (data['jsEventListeners'].hasOwnProperty(listener)) {
            listeners[listener['event']] = listener['js'];
        }
    }
    return listeners;
}

function createChildren(data) {
    let children = [];
    for (let child in data['children']) {
        if (data['children'].hasOwnProperty(child)) {
            children += createComponent(child);
        }
    }
}