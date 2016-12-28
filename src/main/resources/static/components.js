function createComponent(data) {
    switch (data.type) {
        case BUTTON:
            return createButton(data);
        case IMAGE:
            return createImage(data);
        case PANEL:
            return createPanel(data);
        case TEXT:
            return createText(data);
        case TEXTFIELD:
            return createTextField(data);
    }
}

function createButton(data) {
    var button = $('<button>' + data['text'] + '</button>');
    button.setParameter('id', data['id']);
    var style = createStyleString(data);
    if (!style.empty()) button.setAttribute('style', style);
    var listeners = createJsEventListeners(data);
    for (var event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            button.setAttribute(event, listeners[event]);
        }
    }
    return button;
}

function createImage(data) {
    var image = $('<img src="' + data.src + '">');
    image.setAttribute('id', data['id']);
    var style = createStyleString(data);
    if (!style.empty()) image.setAttribute('style', style);
    var listeners = createJsEventListeners(data);
    for (var event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            image.setAttribute(event, listeners[event]);
        }
    }
    return image;
}

function createPanel(data) {
    var panel = $('<section></section>');
    var style = createStyleString(data);
    if (!style.empty()) panel.setAttribute('style', style);
    var children = createChildren(data);
    for (var i = 0; i < children.length; i++) {
        panel.appendChild(children[i]);
    }
    return panel;
}

function createText(data) {
    var paragraph = $('<p>' + data['text'] + '</p>');
    var style = createStyleString(data);
    if (!style.empty()) paragraph.setAttribute('style', style);
    return paragraph;
}

function createTextField(data) {
    var field = $('<input type="text"/>');
    var style = createStyleString(data);
    if (!style.empty()) field.setAttribute('style', style);
    return field;
}

function createStyleString(data) {
    var style = '';
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
    var listeners = {};
    for (var listener in data['jsEventListeners']) {
        if (data['jsEventListeners'].hasOwnProperty(listener)) {
            listeners[listener['event']] = listener['js'];
        }
    }
    return listeners;
}

function createChildren(data) {
    var children = [];
    for (var child in data['children']) {
        if (data['children'].hasOwnProperty(child)) {
            children += createComponent(child);
        }
    }
}