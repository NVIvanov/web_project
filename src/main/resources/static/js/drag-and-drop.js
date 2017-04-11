import { Button, Image, Panel, Text, TextField } from './editor_components.js';

let inc = 0;

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
    const dropTarget = event.currentTarget;
    if (dropTarget.getAttribute('data-type') == 'PANEL') {
        dropTarget.setAttribute('style', 'border: dashed 1px');
    }
    const type = event.dataTransfer.getData('type');
    const html = event.dataTransfer.getData('text/html');
    const id = event.dataTransfer.getData('id');
    if (id != '') {
        const element = $(html);
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
            return <Button/>;
        case 'IMAGE':
            return <Image/>;
        case 'PANEL':
            return <Panel/>;
        case 'TEXT':
            return <Text/>;
        case 'TEXTFIELD':
            return <TextField/>;
    }
}

function remove(id) {
    $('#' + id).remove();
}