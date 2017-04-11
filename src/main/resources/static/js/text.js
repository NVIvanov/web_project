import React, { Component } from 'react';

class Text extends Component {
    render() {
        // return $('<p ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
        //     'style="border: dashed 1px" data-type="TEXT" id="' + inc + '">Lorem ipsum dolor sit amet, ' +
        //     'consectetur adipiscing elit. Fusce interdum semper metus tempus scelerisque. ' +
        //     'Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.' +
        //     '<button onclick="remove(' + inc++ + ')"></button></p>');
        const style = {border: 'dashed 1px'};
        return <p style={style}>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce interdum semper metus tempus
            scelerisque. Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.
            <button>Удалить</button>
        </p>;
    }
}

const textSource = {
    beginDrag(props) {
        return {
            componentId: props.id
        }
    }
};