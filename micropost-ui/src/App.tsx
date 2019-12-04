import React from 'react'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/font-awesome/css/font-awesome.min.css'
import './index.css';

import {PagedList, Header, Form} from './components'

const App: React.FC = () => {
  return (
    <div className="container-fluid">

      <Header/>
      <PagedList 
        items={[{title:"item1"}, {title:"item2"}, {title:"item3"}]}
        pages={3} 
        />
      <Form/>
    </div>
  );
}

export default App;
