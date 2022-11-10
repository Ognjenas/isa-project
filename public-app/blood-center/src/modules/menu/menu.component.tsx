import { Avatar, Button, Flex, Image, Menu, MenuButton, MenuDivider, MenuItem, MenuList } from "@chakra-ui/react"
import { NavLink } from "react-router-dom"
import image from "../../logo.svg"
import styles from "./menu.module.css"


export const AppMenu = () => (
    <Flex boxShadow={'lg'} height='80px' maxH='80px' alignItems='center' position='sticky' justifyContent='space-between'>
        <Flex width='400px' marginLeft={20} gap={10}>
            <NavLink className={styles['menu-item']} to={"/home"}>Home</NavLink>
            <NavLink className={styles['menu-item']} to={"/profile"}>Profile</NavLink>
        </Flex>
        <Flex marginRight={20}>
            <Flex alignItems={'center'}>
                <Menu>
                    <MenuButton
                        as={Button}
                        rounded={'full'}
                        variant={'link'}
                        cursor={'pointer'}
                        minW={0}>
                        <Avatar
                            size={'lg'}
                            src={'https://images.unsplash.com/photo-1493666438817-866a91353ca9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=200&w=200&s=b616b2c5b373a80ffc9636ba24f7a4a9'} />
                    </MenuButton>
                    <MenuList>
                        <MenuItem><NavLink to={styles['/profile']}>Profile</NavLink></MenuItem>
                        <MenuItem><NavLink to={styles['/history']}>History</NavLink></MenuItem>
                        <MenuDivider />
                        <MenuItem><NavLink to={styles['/settings']}>Settings</NavLink></MenuItem>
                    </MenuList>
                </Menu>
            </Flex>
        </Flex>
    </Flex>
)



export default AppMenu