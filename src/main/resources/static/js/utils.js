var page;
var idIncrement = 0;

function createUser() {
    var name = document.getElementById('username').value + '';
    $.ajax({
        type: 'POST',
        url: '/users/',
        contentType: 'application/json',
        data: JSON.stringify({
            username: name,
            password: name + name,
            name: name
        }),
        success: function() {
            window.location.href = '/' + name;
        }
    });
}

function getComponents() {
    $.ajax({
        type: 'GET',
        url: window.location.href.replace('edit', 'get'),
        success: function(data) {
            page = data['id'];
            return false;
        }
    });
    $.ajax({
        type: 'GET',
        url: window.location.href.replace('edit', 'components'),
        success: function(data) {
            for (var i = 0; i < data.length; i++) {
                var element = createComponent(data[i]);
                $('#editor').append($(element));
            }
            return false;
        }
    });
}

function sendComponents(element) {
    var json = [];
    for (var i = 0; i < element.childElementCount; i++) {
        json[i] = convertComponentToJson(element.children[i], true);
    }
    $.ajax({
        type: 'POST',
        url: '/components/',
        contentType: 'application/json',
        data: JSON.stringify(json)
    })
}

function setAttributesForEditing(component, type) {
    console.log(typeof component);
    component.attr('style', component.attr('style') + '; border: dashed 1px');
    component.attr('ondragstart', 'handleDragStartInEditor(event)');
    component.attr('ondrag', 'handleDrag(event)');
    component.attr('ondragend', 'handleDragEnd(event)');
    component.attr('ondragover', 'handleDragOver(event)');
    component.attr('draggable', 'true');
    component.attr('data-type', type);
    component.attr('id', '' + idIncrement++);
}

function convertComponentToJson(component, isRoot) {
    var componentJson = {};
    if (isRoot) componentJson['pageId'] = page;
    componentJson['width'] = component.style.width;
    componentJson['height'] = component.style.height;
    componentJson['margin'] = component.style.margin;
    componentJson['padding'] = component.style.padding;
    componentJson['position'] = component.style.position;
    componentJson['borderRadius'] = component.style.borderRadius;
    componentJson['fontSize'] = component.style.fontSize;
    componentJson['fontFamily'] = component.style.fontFamily;
    componentJson['colorHex'] = component.style.color;
    var type = component.getAttribute('data-type');
    componentJson['type'] = type.toUpperCase();
    if (type == 'button' || type == 'text') {
        componentJson['text'] = component.innerHTML;
    }
    if (type == 'image') {
        componentJson['src'] = component.src;
    }
    var attributes = component.attributes;
    var jsListeners = {};
    for (var i = 0; i < attributes.length; i++) {
        if (attributes[i].name.startsWith('on') &&
            attributes[i].name.indexOf('drag') != -1 &&
            attributes[i].name.indexOf('drop') != -1) {
            jsListeners[attributes[i].name] = attributes[i].value;
        }
    }
    if (component.childElementCount > 0) {
        componentJson['children'] = [];
        for (i = 0; i < component.childElementCount; i++) {
            componentJson['children'][i] = convertComponentToJson(component.children[i], false);
        }
    }
    return componentJson;
}

function createComponent(data) {
    var component;
    switch (data.type) {
        case 'BUTTON':
            component = createButton(data);
            break;
        case 'IMAGE':
            component = createImage(data);
            break;
        case 'PANEL':
            component = createPanel(data);
            break;
        case 'TEXT':
            component = createText(data);
            break;
        case 'TEXTFIELD':
            component = createTextField(data);
    }
    console.log(typeof component);
    setAttributesForEditing(component, data['type']);
    return component;
}

function createButton(data) {
    var button = $('<button>' + data['text'] + '</button>');
    button.attr('id', data['id']);
    var style = createStyleString(data);
    if (style.length > 0) button.attr('style', style);
    var listeners = createJsEventListeners(data);
    for (var event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            button.attr(event, listeners[event]);
        }
    }
    return button;
}

function createImage(data) {
    var image = $('<img src="' + data.src + '">');
    image.attr('id', data['id']);
    var style = createStyleString(data);
    if (style.length > 0) image.attr('style', style);
    var listeners = createJsEventListeners(data);
    for (var event in listeners) {
        if (listeners.hasOwnProperty(event)) {
            image.attr(event, listeners[event]);
        }
    }
    return image;
}

function createPanel(data) {
    var panel = $('<section></section>');
    var style = createStyleString(data);
    if (style.length > 0) panel.attr('style', style);
    var children = createChildren(data);
    for (var i = 0; i < children.length; i++) {
        $(panel).append($(children[i]));
    }
    return panel;
}

function createText(data) {
    var paragraph = $('<p>' + data['text'] + '</p>');
    var style = createStyleString(data);
    if (style.length > 0) paragraph.attr('style', style);
    return paragraph;
}

function createTextField(data) {
    var field = $('<input type="text"/>');
    var style = createStyleString(data);
    if (style.length > 0) field.attr('style', style);
    return field;
}

function createStyleString(data) {
    var style = '';
    if (data['width'] != null) {
        style += 'width: ' + data['width'];
    }
    if (data['height'] != null) {
        if (style.length > 0) style += '; ';
        style += 'height: ' + data['height'];
    }
    if (data['margin'] != null) {
        if (style.length > 0) style += '; ';
        style += 'margin: ' + data['margin'];
    }
    if (data['padding'] != null) {
        if (style.length > 0) style += '; ';
        style += 'padding: ' + data['padding'];
    }
    if (data['position'] != null) {
        if (style.length > 0) style += '; ';
        style += 'position: ' + data['position'];
    }
    if (data['fontSize'] != null) {
        if (style.length > 0) style += '; ';
        style += 'font-size: ' + data['fontSize'];
    }
    if (data['fontFamily'] != null) {
        if (style.length > 0) style += '; ';
        style += 'font-family: ' + data['fontFamily'];
    }
    if (data['colorHex'] != null) {
        if (style.length > 0) style += '; ';
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
    for (var i = 0; i < data['children'].length; i++) {
        children[i] = createComponent(data['children'][i]);
    }
    return children;
}