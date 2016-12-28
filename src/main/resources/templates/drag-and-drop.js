function handleDragStart(event) {
    event.currentTarget.style.opacity = 0.5;
    event.dataTransfer.setData('type', event.currentTarget.getAttribute('data-type'));
    event.dataTransfer.effectAllowed = 'move';
    return false;
}

function handleDragStartInEditor(event) {
    event.currentTarget.style.opacity = 0.5;
    event.dataTransfer.setData('text/html', event.currentTarget.innerHTML);
    event.dataTransfer.effectAllowed = 'move';
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
    $(dropTarget).append(createNewComponent(event.dataTransfer.getData('type')));
    return false;
}

function createNewComponent(type) {
    switch (type) {
        case 'button':
            return $('<button style="border: dashed 1px" ondragstart="handleDragStartInEditor(event)" ' +
                'ondrag="handleDrag(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true">Кнопка</button>');
        case 'image':
            return $('<img ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px; height: 100px; width: 100px" src=""/>');
        case 'panel':
            return $('<section style="height: 100px; border: dashed 1px" ' +
                'ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true"></section>');
        case 'text':
            return $('<p ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
                'style="border: dashed 1px">Lorem ipsum dolor sit amet, consectetur adipiscing elit. ' +
                'Fusce interdum semper metus tempus scelerisque. Mauris ex nisi, ' +
                'facilisis nec ullamcorper eget, tempus interdum libero. Duis commodo ' +
                'sapien nec rhoncus iaculis. Proin faucibus rhoncus augue vel pulvinar. ' +
                'Aenean at justo sed diam rutrum efficitur. Nunc sed odio ac est laoreet ' +
                'pretium eget eu nisi. Curabitur cursus erat diam, eget aliquam neque ' +
                'aliquet et. Sed a tortor leo. Aenean auctor nisl eu eleifend tristique.</p>');
        case 'text_field':
            return $('<input ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
                'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
                'ondragover="handleDragOver(event)" draggable="true" ' +
                'type="text" style="border: dashed 1px"/>');
    }
}