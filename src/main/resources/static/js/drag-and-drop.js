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
    if (dropTarget.getAttribute('data-type') == 'panel') {
        dropTarget.setAttribute('style', 'border: dashed 1px');
    }
    var type = event.dataTransfer.getData('type');
    var html = event.dataTransfer.getData('text/html');
    var id = event.dataTransfer.getData('id');
    if (id != '') {
        var element = $(html);
        element.attr('id', inc++ + '');
        $(dropTarget).append(element);
        $('#' + id).remove();
    } else if (type != '') {
        $(dropTarget).append(createNewComponent(type));
    }
    return false;
}

function createNewComponent(type) {
    switch (type) {
        case 'button':
            return $('<button style="border: dashed 1px" ondragstart="handleDragStartInEditor(event)" ' +
                'ondrag="handleDrag(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true" data-type="button" id="' + inc++
                +'">Кнопка</button>');
        case 'image':
            return $('<img ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px; height: 100px; width: 100px" data-type="image" id="' + inc++ +
                '" src=""/>');
        case 'panel':
            return $('<section style="height: 100px; border: dashed 1px" ' +
                'ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" ' +
                'draggable="true" data-type="panel" id="' + inc++ + '"></section>');
        case 'text':
            return $('<p ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px" data-type="text" id="' + inc++ + '">Lorem ipsum dolor sit amet, ' +
                'consectetur adipiscing elit. Fusce interdum semper metus tempus scelerisque. ' +
                'Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.</p>');
        case 'text_field':
            return $('<input ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true" data-type="text_field" ' +
                'type="text" style="border: dashed 1px" id="' + inc++ + '"/>');
    }
}