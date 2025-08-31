#!/bin/bash

echo "=== GitHub Repository Setup ==="

# Check if git is configured
if ! git config --get user.name > /dev/null; then
    echo "Configuring git with default values..."
    git config --global user.name "SpendAnalyzer Developer"
    git config --global user.email "developer@spendanalyzer.app"
    echo "Git configured with default values. You can change these later."
fi

# Initialize git if not already done
if [ ! -d ".git" ]; then
    echo "Initializing git repository..."
    git init
fi

# Add all files
echo "Adding files to git..."
git add .

# Check if there are any changes to commit
if git diff --cached --quiet; then
    echo "No changes to commit. Repository is already up to date."
else
    # Commit changes
    echo "Committing changes..."
    git commit -m "Initial commit: SpendAnalyzer Android App with GitHub Actions"
fi

# Create main branch if not exists
git branch -M main

# Clean up any existing remotes and locks
echo "Cleaning up git configuration..."
git remote remove origin 2>/dev/null || true
rm -f .git/config.lock .git/index.lock 2>/dev/null || true

# Add the GitHub repository as origin
echo "To connect to GitHub:"
echo "1. Create a repository on GitHub named 'SpendAnalyzerApp'"
echo "2. Run: git remote add origin https://github.com/YOUR_USERNAME/SpendAnalyzerApp.git"
echo "3. Run: git push -u origin main"
echo ""
echo "Repository is ready for GitHub!"