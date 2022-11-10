import './App.css';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Outlet } from 'react-router-dom';
import SideMenu from './modules/menu/side-menu';
import { Flex } from '@chakra-ui/react';

function App() {
  return (
    <div className="App">
      <Flex flex={1}>
        <SideMenu />
        <Flex width='100%' maxH='100vh' height='100vh' overflowY='scroll'>
          <Outlet />
        </Flex>
      </Flex>
      <ToastContainer position='bottom-right' />
    </div>
  );
}

export default App;
