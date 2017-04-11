import React, { Component } from 'react';

class Button extends Component {
    render() {
        //return <button style="border: dashed 1px" ondragstart="handleDragStartInEditor(event)" ' +
        //        'ondrag="handleDrag(event)" ondragend="handleDragEnd(event)" ' +
        //        'ondragover="handleDragOver(event)" draggable="true" data-type="BUTTON" id="' + inc++
        //        +'">Кнопка</button>;
        const style = {border: 'dashed 1px'};
        return <button style={style}>Кнопка</button>;
    }
}

const buttonSource = {
    beginDrag(props) {
        return {
            componentId: props.id
        }
    }
};