import React, { Component } from 'react';

class Panel extends Component {
    render() {
        // return $('<section style="height: 100px; border: dashed 1px" ' +
        //     'ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" ' +
        //     'draggable="true" data-type="PANEL" id="' + inc + '">' +
        //     '<button onclick="remove(' + inc++ + ')">Удалить</button></section>');
        const style = {border: '1px dashed', height: '100px'};
        return <section style={style}><button>Удалить</button></section>;
    }
}

const panelSource = {
    beginDrag(props) {
        return {
            componentId: props.id
        }
    }
};