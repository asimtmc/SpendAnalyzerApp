
# SpendAnalyzer App

[![Android CI](https://github.com/your-username/SpendAnalyzerApp/workflows/Android%20CI/badge.svg)](https://github.com/your-username/SpendAnalyzerApp/actions)

A smart expense tracking Android application that automatically analyzes SMS messages to track spending patterns and categorize transactions.

## Features

- 📱 Automatic SMS parsing for transaction detection
- 📊 Smart categorization of expenses
- 💰 Budget tracking and management
- 📈 Spending analytics and insights
- ⚙️ Customizable categories
- 🔔 Budget alerts and notifications

## Screenshots

*Add screenshots here once available*

## Architecture

This app is built using:
- **Language**: Java
- **Database**: Room (SQLite)
- **Architecture**: MVVM pattern
- **SMS Processing**: Automatic transaction detection from bank SMS
- **UI**: Material Design components

## Project Structure

```
SpendAnalyzerApp/
├── app/
│   ├── src/main/java/com/spendanalyzerapp/
│   │   ├── MainActivity.java
│   │   ├── Transaction.java
│   │   ├── Category.java
│   │   ├── SmsParser.java
│   │   ├── SmsReceiver.java
│   │   └── ...
│   └── src/main/res/
│       ├── layout/
│       ├── values/
│       └── ...
└── build.gradle
```

## Getting Started

### Prerequisites

- Android Studio 4.0+
- JDK 8 or higher
- Android SDK 33+
- Minimum SDK: API 21 (Android 5.0)

### Building the Project

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/SpendAnalyzerApp.git
cd SpendAnalyzerApp
```

2. Open the project in Android Studio or build using command line:
```bash
cd SpendAnalyzerApp
./gradlew assembleDebug
```

3. The APK will be generated in `app/build/outputs/apk/debug/`

### Running Tests

```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Permissions

The app requires the following permissions:
- `READ_SMS`: To read SMS messages for transaction detection
- `RECEIVE_SMS`: To receive new SMS messages
- `INTERNET`: For potential cloud sync features

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## CI/CD

This project uses GitHub Actions for continuous integration:
- **Android CI**: Runs on every push and pull request
- **Release Build**: Triggered on version tags
- **Artifacts**: Debug APKs are uploaded as build artifacts

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues or have questions, please open an issue on GitHub.

## Roadmap

- [ ] Cloud sync functionality
- [ ] Export data to CSV/PDF
- [ ] Advanced analytics and charts
- [ ] Multi-account support
- [ ] Dark theme support
- [ ] Backup and restore functionality
