import React, { Component } from 'react';

class TextField extends Component {
    render() {
        // return $('<input ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
        //     'ondragover="handleDragOver(event)" draggable="true" data-type="TEXTFIELD" ' +
        //     'type="text" style="border: dashed 1px" id="' + inc++ + '"/>');
        const style = {border: 'dashed 1px'};
        return <input type="text" style={style}/>;
    }
}

const textFieldSource = {
    beginDrag(props) {
        return {
            componentId: props.id
        }
    }
};