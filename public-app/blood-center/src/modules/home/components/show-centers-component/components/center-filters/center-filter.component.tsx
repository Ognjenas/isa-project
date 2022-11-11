import { Box, Flex, Input, Select } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import FilterSelect from "../filter-select"


interface FilterValues {
    filterBy: string,
    filterByValue: string,
    sort: string,
    sortBy: string,
}

interface Props {
    onChange: (value: FilterValues) => void
}

export const CenterFilters = ({ onChange }: Props) => {

    const [filterBy, setFilterBy] = useState("")
    const [filterByValue, setFilterByValue] = useState("")
    const [sort, setSort] = useState("")
    const [sortBy, setSortBy] = useState("")


    const filters = [
        {
            val: "name",
            text: "Name"
        },
        {
            val: "country",
            text: "Country"
        },
        {
            val: "city",
            text: "City"
        }
    ]

    const sortByItems = [
        { val: 'name', text: "Name" },
        { val: "description", text: "Description" },
        { val: "averageGrade", text: "Average Grade" },
        { val: "city", text: "City" },
        { val: "country", text: "Country" },
        { val: "address", text: "Address" }
    ]

    const sortItems = [{ val: "asc", text: "Ascending" }, { val: "desc", text: "Descending" }]

    useEffect(() => {
        const values: FilterValues = {
            filterBy,
            filterByValue,
            sortBy,
            sort
        }
        onChange(values)
    }, [filterBy, filterByValue, sortBy, sort])

    return (
        <Flex
            width="100%"
            paddingLeft="20px"
            height="80px"
            position="sticky"
            top={0}
            left={0}
            boxShadow="base"
            backgroundColor="#2e3034"
            alignItems="center"
            justifyContent={"flex-start"}
            gap={15}>

            <Box>
                <FilterSelect
                    value={filterBy}
                    onChange={(e: any) => setFilterBy(e.target.value)}
                    placeholder="Filter by"
                    options={filters} />
            </Box>
            {
                filterBy.trim() !== "" &&
                <Box>
                    <Input
                        placeholder="Enter value..."
                        bg="white"
                        value={filterByValue}
                        onChange={(e: any) => setFilterByValue(e.target.value)} />
                </Box>
            }
            <Box>
                <FilterSelect
                    value={sortBy}
                    onChange={(e: any) => setSortBy(e.target.value)}
                    placeholder="Sort by field"
                    options={sortByItems} />
            </Box>
            {
                sortBy.trim() !== "" &&
                <Box>
                    <FilterSelect
                        value={sort}
                        onChange={(e: any) => setSort(e.target.value)}
                        placeholder="Sort"
                        options={sortItems} />
                </Box>
            }


        </Flex>
    )
}


export default CenterFilters