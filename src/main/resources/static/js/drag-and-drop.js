import React, { Component } from 'react';
import Editor from './Editor';
import Button from './Button';
import Image from './Image';
import Panel from './Panel';
import Text from './Text';
import TextField from './TextField';
import {ComponentTypes} from "./ComponentTypes";

export let inc = 0;

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

export function createNewComponent(type, key) {
    switch (type) {
        case ComponentTypes.BUTTON:
            return <Button text="Кнопка" key={key}/>;
        case ComponentTypes.IMAGE:
            return <Image key={key}/>;
        case ComponentTypes.PANEL:
            return <Panel key={key}/>;
        case ComponentTypes.TEXT:
            return <Text key={key}/>;
        case ComponentTypes.TEXTFIELD:
            return <TextField key={key}/>;
    }
}

function remove(id) {
    $('#' + id).remove();
}
