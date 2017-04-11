import React, { Component } from 'react';
import { DragDropContext } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import ReactDOM from 'react-dom';
import ButtonLabel from './ButtonLabel';
import ImageLabel from './ImageLabel';
import PanelLabel from './PanelLabel';
import TextLabel from './TextLabel';
import TextFieldLabel from './TextFieldLabel';

export class Editor extends Component {
    render() {
        const style = {border: '1px dashed', width: '80%', height: '500px'};
        return <section><Toolbar/><EditorField/></section>;
    }
}

export class Toolbar extends Component {
    render() {
        return <section className="row show-grid container center-block">
            <SaveButton/>
            <ButtonLabel/>
            <ImageLabel/>
            <PanelLabel/>
            <TextLabel/>
            <TextFieldLabel/>
        </section>
    }
}

function SaveButton() {
    return <section className="col-lg-2 text-center"><button>Сохранить</button></section>
}

export const ComponentTypes = {
    BUTTON: 'button',
    IMAGE: 'image',
    PANEL: 'panel',
    TEXT: 'text',
    TEXTFIELD: 'textField'
};

function collect(collect, monitor) {
    return {
        connectDragSource: collect.dragSource(),
        isDragging: monitor.isDragging()
    }
}

const editorTarget = {
    drop(props, monitor) {
        appendComponent(props, monitor.getItem());
    }
};



export default DragDropContext(HTML5Backend)(Editor);

ReactDOM.render(
    <Editor />,
    document.getElementById('editor_container')
);