import React, { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import DashboardServices from '../../services/DashboardServices';
import { GlobalStateContext } from '../../GlobalStateContext'; // Import global state context
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableData,
  DifficultyLabel,
  FilterContainer,
  FilterLabel,
  FilterSelect
} from "./StyledElements";

function Problems() {
  const [state, dispatch] = useContext(GlobalStateContext);  
  const [problems, setProblems] = useState([]);
  const [categoryFilter, setCategoryFilter] = useState("All");
  const [difficultyFilter, setDifficultyFilter] = useState("All");
const navigate = useNavigate(); 
  // Fetch all problems initially
  useEffect(() => {
    DashboardServices.getAllProblems().then(response => {
      setProblems(response.data);
    }).catch(error => {
      console.log(error);
    });
  }, []);

  // Fetch problems by category filter
  const fetchProblemsByCategory = (category) => {
    if (category === "All") {
      // Fetch all problems
      DashboardServices.getAllProblems().then(response => {
        setProblems(response.data);
      }).catch(error => {
        console.log(error);
      });
    } else {
      // Fetch problems by selected category
      DashboardServices.getProblemsByCategory(category).then(response => {
        setProblems(response.data);
      }).catch(error => {
        console.log(error);
      });
    }
  };

  // Fetch problems by difficulty filter
  const fetchProblemsByDifficulty = (difficulty) => {
    if (difficulty === "All") {
      // Fetch all problems
      DashboardServices.getAllProblems().then(response => {
        setProblems(response.data);
      }).catch(error => {
        console.log(error);
      });
    } else {
      // Fetch problems by selected difficulty
      DashboardServices.getProblemsByDifficulty(difficulty).then(response => {
        setProblems(response.data);
      }).catch(error => {
        console.log(error);
      });
    }
  };

  const handleRowClick = (problemId) => {
    // Fetch the problem data by ID from the backend
    DashboardServices.getProblemById(problemId)
      .then(response => {
        // Store the fetched problem data in the global state
        const problemData = response.data;
        dispatch({ type: 'SET_PROBLEM_DATA', payload: problemData });

        // Navigate to the coding editor page once data is set
        navigate(`/coding-editor/${problemId}`);
      })
      .catch(error => {
        console.error("Error fetching problem data:", error);
      });
  };

  // Handle category filter change
  const handleCategoryChange = (event) => {
    const selectedCategory = event.target.value;
    setCategoryFilter(selectedCategory);
    fetchProblemsByCategory(selectedCategory);
  };

  // Handle difficulty filter change
  const handleDifficultyChange = (event) => {
    const selectedDifficulty = event.target.value;
    setDifficultyFilter(selectedDifficulty);
    fetchProblemsByDifficulty(selectedDifficulty);
  };

  return (
    <TableContainer>
      <FilterContainer>
        <div>
          <FilterLabel style={{color: "white"}}>Filter by Category:</FilterLabel>
          <FilterSelect value={categoryFilter} onChange={handleCategoryChange}>
            <option value="All">All</option>
            <option value="Array">Array</option>
            <option value="Linked List">Linked List</option>
            <option value="Dynamic Programming">Dynamic Programming</option>
            <option value="Stack">Stack</option>
            <option value="Binary Search">Binary Search</option>
            <option value="Binary Tree">Binary Tree</option>
            <option value="Intervals">Intervals</option>
            <option value="Two Pointers">Two Pointers</option>
            <option value="Sliding Window">Sliding Window</option>
            <option value="Backtracking">Backtracking</option>
          </FilterSelect>
        </div>
        <div>
          <FilterLabel style={{color: "white"}}>Filter by Difficulty:</FilterLabel>
          <FilterSelect value={difficultyFilter} onChange={handleDifficultyChange}>
            <option value="All">All</option>
            <option value="easy">Easy</option>
            <option value="medium">Medium</option>
            <option value="hard">Hard</option>
          </FilterSelect>
        </div>
      </FilterContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableHeader>Title</TableHeader>
            <TableHeader>Description</TableHeader>
            <TableHeader>Difficulty</TableHeader>
            <TableHeader>Category</TableHeader>
          </TableRow>
        </TableHead>
        <tbody>
          {problems.map((problem) => (
            <TableRow key={problem.id} onClick={() => handleRowClick(problem.id)}>
              <TableData>{problem.title}</TableData>
              <TableData>{problem.description}</TableData>
              <TableData>
                <DifficultyLabel difficulty={problem.difficulty}>
                  {problem.difficulty}
                </DifficultyLabel>
              </TableData>
              <TableData>{problem.category}</TableData>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </TableContainer>
  );
}

export default Problems;
