import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { DragDropContextProvider } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import ButtonLabel from './ButtonLabel';
import ImageLabel from './ImageLabel';
import PanelLabel from './PanelLabel';
import TextLabel from './TextLabel';
import TextFieldLabel from './TextFieldLabel';
import EditorField from './EditorField';



export class Editor extends Component {
    render() {
        return <DragDropContextProvider backend={HTML5Backend}>
            <section>
                <Toolbar/>
                <EditorField/>
            </section>
        </DragDropContextProvider>;
    }
}

function Toolbar() {
    return <section className="row show-grid container center-block">
        <SaveButton/>
        <ButtonLabel/>
        <ImageLabel/>
        <PanelLabel/>
        <TextLabel/>
        <TextFieldLabel/>
    </section>
}

function SaveButton() {
    return <section className="col-lg-2 text-center"><button>Сохранить</button></section>
}

ReactDOM.render(
    <Editor />,
    document.getElementById('editor_container')
);