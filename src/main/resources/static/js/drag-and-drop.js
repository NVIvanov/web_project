var inc = 0;

function handleDragStart(event) {
    event.stopPropagation();
    event.dataTransfer.setData('type', event.currentTarget.getAttribute('data-type'));
    event.dataTransfer.effectAllowed = 'move';
    return false;
}

function handleDragStartInEditor(event) {
    event.stopPropagation();
    event.dataTransfer.setData('text/html', event.currentTarget.outerHTML);
    event.dataTransfer.effectAllowed = 'move';
    event.dataTransfer.setData('id', event.currentTarget.getAttribute('id'));
    return false;
}

function handleDrag(event) {
    event.preventDefault();
    return false;
}

function handleDragEnd(event) {
    event.preventDefault();
    event.currentTarget.style.opacity = 1;
    return false;
}

function handleDragOver(event) {
    event.preventDefault();
    event.dataTransfer.dropEffect = 'move';
    return false;
}

function handleDrop(event) {
    event.preventDefault();
    event.stopPropagation();
    var dropTarget = event.currentTarget;
    if (dropTarget.getAttribute('data-type') == 'PANEL') {
        dropTarget.setAttribute('style', 'border: dashed 1px');
    }
    var type = event.dataTransfer.getData('type');
    var html = event.dataTransfer.getData('text/html');
    var id = event.dataTransfer.getData('id');
    if (id != '') {
        var element = $(html);
        $('#' + id).remove();
        element.attr('id', id);
        $(dropTarget).append(element);
    } else if (type != '') {
        $(dropTarget).append(createNewComponent(type));
    }
    return false;
}

function createNewComponent(type) {
    switch (type) {
        case 'BUTTON':
            return $('<button style="border: dashed 1px" ondragstart="handleDragStartInEditor(event)" ' +
                'ondrag="handleDrag(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true" data-type="BUTTON" id="' + inc++
                +'">Кнопка</button>');
        case 'IMAGE':
            return $('<img ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px; height: 100px; width: 100px" data-type="IMAGE" id="' + inc++ +
                '" src=""/>');
        case 'PANEL':
            return $('<section style="height: 100px; border: dashed 1px" ' +
                'ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" ' +
                'draggable="true" data-type="PANEL" id="' + inc + '">' +
                '<button onclick="remove(' + inc++ + ')">Удалить</button></section>');
        case 'TEXT':
            return $('<p ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px" data-type="TEXT" id="' + inc + '">Lorem ipsum dolor sit amet, ' +
                'consectetur adipiscing elit. Fusce interdum semper metus tempus scelerisque. ' +
                'Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.' +
                '<button onclick="remove(' + inc++ + ')"></button></p>');
        case 'TEXTFIELD':
            return $('<input ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true" data-type="TEXTFIELD" ' +
                'type="text" style="border: dashed 1px" id="' + inc++ + '"/>');
    }
}

function remove(id) {
    $('#' + id).remove();
}