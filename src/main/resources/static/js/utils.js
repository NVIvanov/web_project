var page;

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
                document.getElementById('editor').appendChild(createComponent(data[i]));
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
    console.log(JSON.stringify(json));
    $.ajax({
        type: 'POST',
        url: '/components/',
        contentType: 'application/json',
        data: JSON.stringify(json)
    })
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
        componentJson['text'] = componentJson.innerHTML;
    }
    var attributes = component.attributes;
    var jsListeners = {};
    for (var i = 0; i < attributes.length; i++) {
        if (attributes[i].name.startsWith('on')) {
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